package com.example.taskmanager.controllerTest;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.taskmanager.DTOs.DepartamentoDTO;
import com.example.taskmanager.service.DepartamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartamentoService departamentoService;

    @Test
    public void testListarDepartamentos() throws Exception {
        DepartamentoDTO departamento1 = new DepartamentoDTO(1L, "Departamento 1");
        DepartamentoDTO departamento2 = new DepartamentoDTO(2L, "Departamento 2");
        List<DepartamentoDTO> departamentos = Arrays.asList(departamento1, departamento2);
        when(departamentoService.buscarDepartamentosInfo()).thenReturn(departamentos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/departamentos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Departamento 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantidadePessoas").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantidadeTarefas").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Departamento 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantidadePessoas").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantidadeTarefas").value(7));
    }

}
