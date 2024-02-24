package com.example.taskmanager.controller;

import com.example.taskmanager.model.Pessoa;
import com.example.taskmanager.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping

    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.adicionarPessoa(pessoa);
    }
    @PutMapping ("/{id}")
    public Pessoa atualizarPessoa(@PathVariable Long id,
                                  @RequestBody Pessoa pessoaAtualizada) {
        return pessoaService.atualizarPessoa(id,pessoaAtualizada);

    }

    @DeleteMapping("/{id}")
    public void removerPessoa(@PathVariable Long id) {
        pessoaService.removerPessoa(id);
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.buscarTodasPessoas();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        return pessoaService.buscarPessoasPorId(id);
    }


}

