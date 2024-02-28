package com.example.taskmanager.service;

import com.example.taskmanager.DTOs.GetPessoaDTO;
import com.example.taskmanager.dao.PessoaRepository;
import com.example.taskmanager.model.Departamento;
import com.example.taskmanager.model.Pessoa;
import com.example.taskmanager.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    private final  DepartamentoService departamentoService;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, DepartamentoService departamentoService) {
        this.pessoaRepository = pessoaRepository;
        this.departamentoService = departamentoService;
    }
    public Pessoa adicionarPessoa(Pessoa pessoa) {
        String nomeDepartamento = pessoa.getDepartamento().getNome();

        if(nomeDepartamento == null) {
            throw new IllegalArgumentException("Departamento não pode ser nulo");
        }
        Departamento departamento = this.departamentoService.buscarOuCriarDepartamento(nomeDepartamento);
        pessoa.setDepartamento(departamento);
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Optional<Pessoa> optionalPessoa  = pessoaRepository.findById(id);
        if(optionalPessoa.isPresent()) {
            Pessoa pessoaExistente = optionalPessoa.get();
            pessoaExistente.setNome(pessoaAtualizada.getNome());
            pessoaExistente.setDepartamento((pessoaAtualizada.getDepartamento() == null
                  ? pessoaExistente.getDepartamento() : pessoaAtualizada.getDepartamento()));
            pessoaExistente.setTarefas(pessoaAtualizada.getTarefas() == null
              ? pessoaExistente.getTarefas() : pessoaAtualizada.getTarefas());
            return pessoaRepository.save(pessoaExistente);
        } else {
            return null;
        }
    }

    public void removerPessoa(Long id) {
        pessoaRepository.deleteAllById(Collections.singleton(id));
    }

    public List<GetPessoaDTO> buscarTodasPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(this::pessoaMapper)
                .toList();
    }

    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    private Duration calcularDuracaoTotal(List<Tarefa> tarefas) {
        return tarefas.stream()
                .map(Tarefa::getDuracao)
        .filter(duracao -> duracao !=null)
                .reduce(Duration::plus)
                .orElse(Duration.ZERO);
    }

    private String duracaoFormatada(Duration duracao) {
        long horas = duracao.toHours();
        long minutos = duracao.minusHours(horas).toMinutes();
        return String.format("%d horas e %d minutos", horas, minutos);
    }

    public GetPessoaDTO pessoaMapper(Pessoa pessoa) {
        GetPessoaDTO pessoaDTO = new GetPessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setDepartamento(pessoa.getDepartamento().getNome());
        pessoaDTO.setTotalHoras(duracaoFormatada(calcularDuracaoTotal(pessoa.getTarefas())));
        return pessoaDTO;
    }

    private String mediaDuracaoTarefas(List<Tarefa> tarefas) {
        if(tarefas.isEmpty()) {
            return "0 horas e 0 minutos";
        }

        Duration duracaoTotal = calcularDuracaoTotal(tarefas);
        long horas = duracaoTotal.toHours() / tarefas.size();
        long minutos = duracaoTotal.minusHours(horas * tarefas.size()).toMinutes() / tarefas.size();
        return String.format("%d horas e %d minutos", horas, minutos);
    }

    public GetPessoaDTO getMediaPessoaPeriodo(String nome, String inicio, String fim) {
        List<Pessoa> pessoas = pessoaRepository.findByNome(nome);
        if(pessoas.isEmpty()) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }

        List<Tarefa> tarefas = pessoas.stream()
                .map(Pessoa::getTarefas)
                .flatMap(List::stream)
                .filter(tarefa -> tarefa.getDataCriacao().isAfter(LocalDate.parse(inicio).atStartOfDay())
                && tarefa.getDataCriacao().isBefore(LocalDate.parse(fim).atStartOfDay()))
                .toList();
        GetPessoaDTO pessoaDTO = pessoaMapper(pessoas.get(0));
        pessoaDTO.setMediaHoras(mediaDuracaoTarefas(tarefas));

        return pessoaDTO;
    }

}
