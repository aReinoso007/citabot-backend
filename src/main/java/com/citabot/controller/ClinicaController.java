package com.citabot.controller;

import com.citabot.interfaceService.IClinicaService;
import com.citabot.model.Clinica;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/clinica")
public class ClinicaController {

    @Autowired
    IClinicaService service;
    /* Funciona */
    @GetMapping(produces = "application/json")
    public List<Clinica> listar(){
        return service.listar();
    }
    /* Probar */
    @GetMapping("/clinica/query")
    public List<Clinica> getByNombre(@RequestParam String nombre){
        return service.listarByNombre(nombre);
    }

    @GetMapping( path = "/{id}")
    public Optional<Clinica> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    /*Funciona */
    @PostMapping
    public Clinica guardarClinica(@RequestBody Clinica clinica){
        return service.save(clinica);
    }

    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        boolean ok = service.delete(id);
        if(ok){
            return "Success";
        }else {
            return "Failed";
        }
    }





}
