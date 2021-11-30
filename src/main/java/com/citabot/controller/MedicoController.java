package com.citabot.controller;

import com.citabot.interfaceService.IMedicoService;
import com.citabot.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    IMedicoService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Medico> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public Medico save(@RequestBody Medico medico){
        return service.save(medico);
    }

    @GetMapping("/query")
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> getByMedicoEspecialidad(@RequestParam int idEspecialidad){
        return service.Listar_medicos_especialidad(idEspecialidad);
    }

}
