package com.example.taskmanager.dao;

import com.example.taskmanager.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    public Optional<Departamento> findByNome(String nome);
}
