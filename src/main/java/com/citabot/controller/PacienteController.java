package com.citabot.controller;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping(produces = "application/json")
    public List<Paciente> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    public Paciente getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Paciente p){
        Paciente pacientedb = service.save(p);
        if(pacientedb == null){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(pacientedb, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Paciente paciente){
        Paciente pacienteDB = null;
        pacienteDB = service.findById(id);
        if(pacienteDB!=null){
            service.update(id, paciente);
            pacienteDB = service.findById(id);
            return new ResponseEntity<>(pacienteDB, HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        String message = null;
        message = service.delete(id);
        if(message.equals("SUCCESS")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }


}
