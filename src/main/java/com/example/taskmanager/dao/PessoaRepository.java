package com.example.taskmanager.dao;

import com.example.taskmanager.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeAndDepartamento(String nome, String departamento);

    List<Pessoa> findByDepartamentoOrderByNome(String departamento);

    List<Object[]> countTarefasByDepartamento(String departamento);

    List<Object[]> sumDuracaoTarefasByDepartamento(String departamento);
    List<Object[]> avgDuracaoTarefasByDepartamento(String departamento);
    List<Pessoa> findByDepartamentoOrderByTarefasSizeDesc(String departamento);


}
