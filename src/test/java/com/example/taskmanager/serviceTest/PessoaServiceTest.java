package com.example.taskmanager.serviceTest;

import com.example.taskmanager.DTOs.GetPessoaDTO;
import com.example.taskmanager.dao.PessoaRepository;
import com.example.taskmanager.model.Pessoa;
import com.example.taskmanager.service.DepartamentoService;
import com.example.taskmanager.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PessoaServiceTest{

    @MockBean
    private PessoaRepository pessoaRepository;

    @MockBean
    private DepartamentoService departamentoService;

    @Test
    public void testBuscarTodasPessoas() {
        PessoaService pessoaService = new PessoaService(pessoaRepository, departamentoService);
        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);
        Mockito.when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<GetPessoaDTO> result = pessoaService.buscarTodasPessoas();
        assertEquals(2, result.size());
        assertEquals(pessoa1, result.get(0));
        assertEquals(pessoa2, result.get(1));
    }

    @Test
    public void testBuscarPessoaPorId_Existente() {
        PessoaService pessoaService = new PessoaService(pessoaRepository, departamentoService);
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        Optional<Pessoa> result = pessoaService.buscarPessoaPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(pessoa, result.get());
    }

    @Test
    public void testBuscarPessoaPorId_Inexistente() {
        PessoaService pessoaService = new PessoaService(pessoaRepository, departamentoService);
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Pessoa> result = pessoaService.buscarPessoaPorId(1L);
        assertFalse(result.isPresent());
    }


}
