package com.example.taskmanager.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TarefaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private LocalTime inicioData;
    private LocalDateTime fimData;
    private String departamento;
    private String duracaoFormatada;
    private Boolean finalizada;
    private LocalDateTime dataCriacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public LocalTime getInicioData() {
        return inicioData;
    }

    public void setInicioData(LocalTime inicioData) {
        this.inicioData = inicioData;
    }

    public LocalDateTime getFimData() {
        return fimData;
    }

    public void setFimData(LocalDateTime fimData) {
        this.fimData = fimData;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDuracaoFormatada() {
        return duracaoFormatada;
    }

    public void setDuracaoFormatada(String duracaoFormatada) {
        this.duracaoFormatada = duracaoFormatada;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
