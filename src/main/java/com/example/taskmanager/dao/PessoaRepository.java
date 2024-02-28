package com.example.taskmanager.dao;

import com.example.taskmanager.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public List<Pessoa> findByNome(String nome);
}
