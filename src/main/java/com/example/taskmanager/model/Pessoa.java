package com.example.taskmanager.model;

import jakarta.persistence.*;


import java.util.List;


@Entity

public class Pessoa {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String nome;
   private String departamento;
   @OneToMany(mappedBy = "pessoaAlocada")
    private List<Tarefa> tarefas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
