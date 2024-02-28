package com.example.taskmanager.serviceTest;

import com.example.taskmanager.dao.DepartamentoRepository;
import com.example.taskmanager.model.Departamento;
import com.example.taskmanager.service.DepartamentoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest

public class DepartamentoServiceTest {
    @MockBean
    private DepartamentoRepository departamentoRepository;

    @Test
    public void testBuscarTodosDepartamentos() {
        DepartamentoService departamentoService = new DepartamentoService(departamentoRepository);
        Departamento departamento1 = new Departamento();
        Departamento departamento2 = new Departamento();
        List<Departamento> departamentos = Arrays.asList(departamento1, departamento2);
        when(departamentoRepository.findAll()).thenReturn(departamentos);

        List<Departamento> result = departamentoService.buscarTodosDepartamentos();
        assertEquals(2, result.size());
        assertEquals(departamento1, result.get(0));
        assertEquals(departamento2, result.get(1));
    }

    @Test
    public void testBuscarDepartamentoPorId_Existente() {
        DepartamentoService departamentoService = new DepartamentoService(departamentoRepository);
        Departamento departamento = new Departamento();
        when(departamentoRepository.findById(1L)).thenReturn(Optional.of(departamento));

        Optional<Departamento> result = departamentoService.buscarDepartamentoPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(departamento, result.get());
    }

    @Test
    public void testBuscarDepartamentoPorId_Inexistente() {
        DepartamentoService departamentoService = new DepartamentoService(departamentoRepository);
        when(departamentoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Departamento> result = departamentoService.buscarDepartamentoPorId(1L);
        Assertions.assertFalse(result.isPresent());
    }

}
