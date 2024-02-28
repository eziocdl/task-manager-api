package com.example.taskmanager.service;

import com.example.taskmanager.DTOs.DepartamentoDTO;
import com.example.taskmanager.dao.DepartamentoRepository;
import com.example.taskmanager.model.Departamento;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public Departamento adicionarDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento atualizarDepartamento(Long id, Departamento departamentoAtualizado) {
        Optional<Departamento> optionalDepartamento = departamentoRepository.findById(id);
        if (((Optional<?>) optionalDepartamento).isPresent()) {
            Departamento departamentoExistente = optionalDepartamento.get();
            departamentoExistente.setNome(departamentoAtualizado.getNome());
            return departamentoRepository.save(departamentoExistente);
        } else {
            return null;
        }
    }

    public void removerDepartamento(Long id) {
        departamentoRepository.deleteAllById(Collections.singleton(id));
    }

    public List<Departamento> buscarTodosDepartamentos() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> buscarDepartamentoPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    public Departamento buscarOuCriarDepartamento(String nome) {
        return departamentoRepository.findByNome(nome)
                .orElseGet(() -> {
                    Departamento novoDepartamento = new Departamento();
                    novoDepartamento.setNome(nome);
                    return departamentoRepository.save(novoDepartamento);
                });
    }

    public List<DepartamentoDTO> buscarDepartamentosInfo() {
        return departamentoRepository.findAll().stream()
                .map(departamento -> {
                    DepartamentoDTO departamentoDTO = new DepartamentoDTO(departamento.getId(), departamento.getNome());
                    departamentoDTO.setQuantidadePessoas(departamento.getPessoas().size());
                    departamentoDTO.setQuantidadeTarefas(departamento.getTarefas().size());
                    return departamentoDTO;
                })
                .collect(Collectors.toList());
    }
}
