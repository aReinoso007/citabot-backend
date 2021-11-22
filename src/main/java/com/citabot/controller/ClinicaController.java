package com.citabot.controller;

import com.citabot.interfaceService.IClinicaService;
import com.citabot.model.Clinica;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    /* Funciona */
    @GetMapping("/query")
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
        String resp;
        return service.delete(id);
    }

    @PutMapping(value = "/add_direccion/{id}",  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> addClinicaDireccion(@PathVariable("id") int id, @RequestParam("idDir") int idDir){
        Clinica clinica = null;
        clinica = service.findById(id).get();
        if(clinica!=null){
            service.addDireccion(idDir, id);
            return new ResponseEntity<>(clinica, HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    //@PutMapping()

}
