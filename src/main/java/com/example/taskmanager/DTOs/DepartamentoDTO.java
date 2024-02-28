package com.example.taskmanager.DTOs;

public class DepartamentoDTO {
    private Long id;
    private String nome;
    private Integer quantidadePessoas;
    private Integer quantidadeTarefas;

    public DepartamentoDTO() {

    }

    public DepartamentoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public Integer getQuantidadeTarefas() {
        return quantidadeTarefas;
    }

    public void setQuantidadeTarefas(Integer quantidadeTarefas) {
        this.quantidadeTarefas = quantidadeTarefas;
    }
}
