package com.example.taskmanager.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.taskmanager.dao.TarefaRepository;
import com.example.taskmanager.model.Tarefa;
import com.example.taskmanager.service.DepartamentoService;
import com.example.taskmanager.service.PessoaService;
import com.example.taskmanager.service.TarefaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TarefaServiceTest {

    @MockBean
    private TarefaRepository tarefaRepository;

    @MockBean
    private DepartamentoService departamentoService;

    @MockBean
    private PessoaService pessoaService;

    @Test
    public void testBuscarTodasTarefas() {
        TarefaService tarefaService = new TarefaService(tarefaRepository, departamentoService, pessoaService);
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2);
        when(tarefaRepository.findAll()).thenReturn(tarefas);

        List<Tarefa> result = tarefaService.buscarTodasTarefas();
        Assertions.assertEquals(2, result.size());
        assertEquals(tarefa1, result.get(0));
        assertEquals(tarefa2, result.get(1));
    }

    @Test
    public void testBuscarTarefaPorId_Existente() {
        TarefaService tarefaService = new TarefaService(tarefaRepository, departamentoService, pessoaService);
        Tarefa tarefa = new Tarefa();
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        Optional<Tarefa> result = tarefaService.buscarTarefasPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(tarefa, result.get());
    }

    @Test
    public void testBuscarTarefaPorId_Inexistente() {
        TarefaService tarefaService = new TarefaService(tarefaRepository, departamentoService, pessoaService);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Tarefa> result = tarefaService.buscarTarefasPorId(1L);
        assertFalse(result.isPresent());
    }


}

