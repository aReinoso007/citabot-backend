package com.citabot.controller;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService service;
    /*Este listar verificar con Chris si necesita algo publico */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Paciente>> listar(){
        List<Paciente> pacientes = service.listar();
        if(pacientes.isEmpty()){
            return new ResponseEntity<>(pacientes, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Paciente getById(@PathVariable("id") int id){
        return service.findById(id);
    }




    /*Formato de fecha: anio-mes-dia */
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
