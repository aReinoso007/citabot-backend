package com.citabot.controller;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Paciente save(@RequestBody Paciente p){

        return service.save(p);
    }


}
