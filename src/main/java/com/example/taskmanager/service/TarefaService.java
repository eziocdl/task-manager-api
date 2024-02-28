package com.example.taskmanager.service;

import com.example.taskmanager.DTOs.TarefaDTO;
import com.example.taskmanager.dao.TarefaRepository;
import com.example.taskmanager.model.Departamento;
import com.example.taskmanager.model.Pessoa;
import com.example.taskmanager.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final DepartamentoService departamentoService;
    private final PessoaService pessoaService;
    @Autowired
    public TarefaService(
            TarefaRepository tarefaRepository,
            DepartamentoService departamentoService,
            PessoaService pessoaService
    ) {
        this.departamentoService = departamentoService;
        this.tarefaRepository = tarefaRepository;
        this.pessoaService = pessoaService;

    }

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        LocalDateTime atualData = LocalDateTime.now();
        if(tarefa.getPrazo().isBefore(atualData.toLocalDate())) {
            throw new IllegalArgumentException("Prazo não pode ser antes da data atual");

        };

        Departamento departamento = this.departamentoService.buscarOuCriarDepartamento(tarefa.getDepartamento().getNome());
        tarefa.setDepartamento(departamento);

        tarefa.setDataCriacao(atualData);
        return tarefaRepository.save(tarefa);

    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if(optionalTarefa.isPresent()) {
            Tarefa tarefaExistente = optionalTarefa.get();
            tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setPrazo(tarefaAtualizada.getPrazo());
            tarefaExistente.setDepartamento(tarefaAtualizada.getDepartamento());
            tarefaExistente.setDuracao(tarefaAtualizada.getDuracao());
            tarefaExistente.setPessoaAlocada(tarefaAtualizada.getPessoaAlocada());
            tarefaExistente.setFinalizada(tarefaAtualizada.isFinalizada());
            return tarefaRepository.save(tarefaExistente);
        }else {
            return null;
        }

    }

    public void removerTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> buscarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarTarefasPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa alocarTarefa(Long id, Long idPessoa) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        Optional<Pessoa> optionalPessoa = pessoaService.buscarPessoaPorId(idPessoa);
        Optional<Departamento> optionalDepartamento = departamentoService.buscarDepartamentoPorId(optionalPessoa.get().getDepartamento().getId());

        if(!optionalPessoa.isPresent()) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
        if (!optionalDepartamento.isPresent()) {
            throw new IllegalArgumentException("Essa pessoa não pertence a esse departamento");
        }
        if(optionalTarefa.isPresent()) {
            Tarefa tarefaExistente = optionalTarefa.get();
            tarefaExistente.setPessoaAlocada(optionalPessoa.get());
            return tarefaRepository.save(tarefaExistente);
        } else {
            return null;
        }
    }

    public Tarefa comecarTarefa(Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.get().isFinalizada()) {
            throw new IllegalArgumentException("Tarefa já finalizada");
        }

        if (optionalTarefa.get().getInicioData() != null) {
            throw new IllegalArgumentException("Tarefa já iniciada");
        }

        if (optionalTarefa.isPresent()) {
            Tarefa tarefaExistente = optionalTarefa.get();
            tarefaExistente.setInicioData(LocalDateTime.now());
            return tarefaRepository.save(tarefaExistente);
        } else {
            return null;
        }

    }

    public TarefaDTO finalizarTarefa(Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if(optionalTarefa.isPresent()) {
            Tarefa tarefaExistente = optionalTarefa.get();
            tarefaExistente.setFinalizada(true);
            tarefaExistente.setFimData(LocalDateTime.now());

            Duration duracao = calcularDuracao(tarefaExistente.getInicioData(), tarefaExistente.getFimData());
            tarefaExistente.setDuracao(duracao);

            Tarefa tarefaFinalizada = tarefaRepository.save(tarefaExistente);
            return tarefaMapper(tarefaFinalizada);
        }else {
            return null;
        }
    }

    private Duration calcularDuracao(LocalDateTime inicio, LocalDateTime fim) {
        return Duration.between(inicio, fim);
    }

    public TarefaDTO tarefaMapper(Tarefa tarefa) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setId(tarefa.getId());
        tarefaDTO.setTitulo(tarefa.getTitulo());
        tarefaDTO.setDescricao(tarefa.getDescricao());
        tarefaDTO.setPrazo(tarefa.getPrazo());
        tarefaDTO.setInicioData(LocalTime.from(tarefa.getInicioData()));
        tarefaDTO.setFimData(tarefa.getFimData());
        tarefaDTO.setDepartamento(tarefa.getDepartamento().getNome());
        tarefaDTO.setDuracaoFormatada(formatarDuracao(tarefa.getDuracao()));
        tarefaDTO.setFinalizada(tarefa.isFinalizada());
        tarefaDTO.setDataCriacao(tarefa.getDataCriacao());
        return tarefaDTO;
    }


    private String formatarDuracao(Duration duracao) {
        long horas = duracao.toHours();
        long minutos = duracao.toMinutes() % 60;
        return String.format("%02d:%02d", horas, minutos);
    }

    public List<Tarefa> buscarTarefasPendentes() {
        return tarefaRepository.findAll().stream()
                .filter(tarefa -> !tarefa.isFinalizada() && tarefa.getPrazo() != null)
                .sorted(Comparator.comparing(Tarefa::getPrazo))
                .limit(3)
                .collect(Collectors.toList());
    }





}
