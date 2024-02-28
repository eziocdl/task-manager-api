package com.example.taskmanager.controllerTest;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.taskmanager.DTOs.GetPessoaDTO;
import com.example.taskmanager.service.PessoaService;
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
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @Test
    public void testListarPessoas() throws Exception {
        GetPessoaDTO pessoa1 = new GetPessoaDTO();
        GetPessoaDTO pessoa2 = new GetPessoaDTO();
        List<GetPessoaDTO> pessoas = Arrays.asList(pessoa1, pessoa2);
        when(pessoaService.buscarTodasPessoas()).thenReturn(pessoas);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("João"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departamento").value("Departamento 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalHoras").value("2 horas e 30 minutos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Maria"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departamento").value("Departamento 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalHoras").value("1 hora e 45 minutos"));
    }


}

