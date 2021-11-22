package com.citabot.controller;

import com.citabot.interfaceService.IEnfermedadService;
import com.citabot.model.Enfermedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/enfermedad")
public class EnfermedadController {

    @Autowired
    IEnfermedadService service;

    @GetMapping(produces = "application/json")
    public List<Enfermedad> listar(){
        return service.listar();
    }

    @GetMapping("/query")
    public List<Enfermedad> getByNombre(@RequestParam String nombre){
        return service.listarByNombre(nombre);
    }

    @GetMapping(path = "/{id}")
    Optional<Enfermedad> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public Enfermedad guardarEnfermedad(@RequestBody Enfermedad enfermedad){
        return service.save(enfermedad);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        String resp = service.delete(id);
        return resp;
    }

}
