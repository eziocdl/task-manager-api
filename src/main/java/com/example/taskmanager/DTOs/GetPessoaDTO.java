package com.example.taskmanager.DTOs;

public class GetPessoaDTO {
    private Long id;
    private String nome;
    private String departamento;
    private String totalHoras;
    private String mediaHoras;

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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(String totalHoras) {
        this.totalHoras = totalHoras;
    }

    public String getMediaHoras() {
        return mediaHoras;
    }

    public void setMediaHoras(String mediaHoras) {
        this.mediaHoras = mediaHoras;
    }
}
