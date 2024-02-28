package com.example.taskmanager.controller;

import com.example.taskmanager.DTOs.DepartamentoDTO;
import com.example.taskmanager.service.DepartamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")

public class DepartamentoController {
    private final DepartamentoService departamentoService;
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }
    @GetMapping
    public List<DepartamentoDTO> listarDepartamentos() {
        return this.departamentoService.buscarDepartamentosInfo();

    }


}
