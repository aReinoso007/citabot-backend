package com.citabot.controller;

import com.citabot.interfaceService.IClinicaService;
import com.citabot.model.Clinica;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
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
    public ResponseEntity<?> guardarClinica(@RequestBody Clinica clinica){
        Clinica clinicaDb = null;
        clinicaDb = service.save(clinica);
        if(clinicaDb!=null){
            return new ResponseEntity<>(clinicaDb, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        return service.delete(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClinica(@PathVariable("id") int id, @RequestBody Clinica clinica){
        Clinica clinicaDB = null;
        clinicaDB = service.findById(id).get();
        if(clinicaDB!=null){
            clinicaDB.setNombreClinica(clinica.getNombreClinica());
            service.update(clinicaDB);
            return new ResponseEntity<>(clinicaDB, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}
