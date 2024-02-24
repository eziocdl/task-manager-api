package com.example.taskmanager.dao;

import com.example.taskmanager.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByDepartamento(String departamento);

    List<Tarefa> findByFinalizadaFalse();

    List<Tarefa> findByPrazoBefore(LocalDate prazo);

    List<Tarefa> findByDepartamentoAAndPessoaAlocadaNome(String departamento, String nome);

    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);
}
