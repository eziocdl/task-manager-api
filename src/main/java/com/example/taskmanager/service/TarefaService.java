package com.example.taskmanager.service;

import com.example.taskmanager.model.Tarefa;
import com.example.taskmanager.dao.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefaExistente = optionalTarefa.get();
            tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setPrazo(tarefaAtualizada.getPrazo());
            tarefaExistente.setDepartamento(tarefaAtualizada.getDepartamento());
            tarefaExistente.setDuracao(tarefaAtualizada.getDuracao());
            tarefaExistente.setPessoaAlocada(tarefaAtualizada.getPessoaAlocada());
            tarefaExistente.setFinalizada(tarefaAtualizada.isFinalizada());
            return tarefaRepository.save(tarefaExistente);
        } else {
            return null;
        }
    }

    public void removerTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> buscarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }
}
