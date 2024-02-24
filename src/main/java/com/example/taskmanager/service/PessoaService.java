package com.example.taskmanager.service;

import com.example.taskmanager.model.Pessoa;
import com.example.taskmanager.dao.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa adicionarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        if(optionalPessoa.isPresent()) {
            Pessoa pessoaExistente = optionalPessoa.get();
            pessoaExistente.setNome(pessoaAtualizada.getNome());
            pessoaExistente.setId(Long.valueOf(pessoaAtualizada.getDepartamento()));

            return pessoaRepository.save(pessoaExistente);
        } else {
            return null;
        }

    }

    public  void  removerPessoa(Long id) {
        pessoaRepository.deleteAllById(Collections.singleton(id));
    }

    public List<Pessoa> buscarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoasPorId(Long id) {
        return pessoaRepository.findById(id);
    }


}
