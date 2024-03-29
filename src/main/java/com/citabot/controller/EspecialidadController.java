package com.citabot.controller;


import com.citabot.interfaceService.IEspecialidadService;

import com.citabot.model.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private/especialidad")

public class EspecialidadController {

    @Autowired
    IEspecialidadService service;


    @GetMapping(produces = "application/json")
    public List<Especialidad> listar(){
        return service.listar();
    }

    @GetMapping("/query")
    public List<Especialidad> getByNombre(@RequestParam String nombre){
        return service.listarByNombre(nombre);
    }

    @GetMapping(path = "/disponibles/{id}")
    public List<Especialidad> getDisponiblesParaMedico(@PathVariable("id") int idMedico){
        return service.listarDisponiblesParaMedico(idMedico);

    }

    @GetMapping(path = "/registradas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Especialidad> getRegistradasPorMEdico(@PathVariable("id") int id){
        return service.listarRegistradasPorMedico(id);
    }

    @GetMapping(path = "/{id}")
    Optional<Especialidad> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public Especialidad guardarEspecialidad(@RequestBody Especialidad especialidad){
        return service.save(especialidad);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        String resp = service.delete(id);
        return resp;
    }
}
