package com.citabot.controller;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping(produces = "application/json")
    public List<Paciente> listar(){
        return service.listar();
    }

    //@GetMapping("/patients/{id}")

    @PostMapping("pacientes/")
    public String save(@Validated Paciente p, Model model){
        service.save(p);
        return "redirect:/pacientes";
    }


}
