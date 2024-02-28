package com.example.taskmanager.controllerTest;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.taskmanager.model.Tarefa;
import com.example.taskmanager.service.TarefaService;
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
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    @Test
    public void testListarTarefas() throws Exception {
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2);
        when(tarefaService.buscarTodasTarefas()).thenReturn(tarefas);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tarefas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Tarefa 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descricao").value("Descrição da tarefa 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].prazo").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].finalizada").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].titulo").value("Tarefa 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].descricao").value("Descrição da tarefa 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].prazo").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].finalizada").value(true));
    }


}

