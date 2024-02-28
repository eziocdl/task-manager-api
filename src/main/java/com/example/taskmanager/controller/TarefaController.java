package com.example.taskmanager.controller;

import com.example.taskmanager.DTOs.TarefaDTO;
import com.example.taskmanager.model.Tarefa;
import com.example.taskmanager.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public Tarefa adicionarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.adicionarTarefa(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefaService.atualizarTarefa(id, tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void removerTarefa(@PathVariable Long id) {
        tarefaService.removerTarefa(id);
    }

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaService.buscarTodasTarefas();
    }
    @GetMapping("/{id}")
    public Optional<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        return tarefaService.buscarTarefasPorId(id);
    }

    @PutMapping("/alocar/{id}")
    public Tarefa alocarTarefa(@PathVariable Long id, @RequestBody Long idPessoa) {
        return tarefaService.alocarTarefa(id, idPessoa);
    }

    @PutMapping("/finalizar/{id}")
    public TarefaDTO finalizarTarefa(@PathVariable Long id) {
        return tarefaService.finalizarTarefa(id);
    }
    @PutMapping("/comecar/{id}")
    public Tarefa comecarTarefa(@PathVariable Long id) {
        return tarefaService.comecarTarefa(id);
    }
    @GetMapping("/pendentes")
    public List<Tarefa> listarTarefasPendentes() {
        return tarefaService.buscarTarefasPendentes();
    }


}
