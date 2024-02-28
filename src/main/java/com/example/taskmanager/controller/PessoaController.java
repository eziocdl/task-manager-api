package com.example.taskmanager.controller;

import com.example.taskmanager.DTOs.GetPessoaDTO;
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
        return pessoaService.atualizarPessoa(id, pessoaAtualizada);
    }

    @DeleteMapping ("/{id}")
    public void removerPessoa(@PathVariable Long id) {
        pessoaService.removerPessoa(id);
    }

    @GetMapping
    public List<GetPessoaDTO> listarPessoas() {
        return pessoaService.buscarTodasPessoas();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        return pessoaService.buscarPessoaPorId(id);
    }

    @GetMapping("/gastos")
    // pegue o nome e período por query param

    public GetPessoaDTO calcularGastosComPessoas(@RequestParam String nome, @RequestParam String dataInicio, @RequestParam String dataFim) {
        return pessoaService.getMediaPessoaPeriodo(nome, dataInicio, dataFim);
    }
}
