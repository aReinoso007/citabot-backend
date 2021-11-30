package com.citabot.controller;

import com.citabot.interfaceService.IDireccionClinicaService;
import com.citabot.model.DireccionClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/direccion_clinica")
public class DireccionClinicaController {

    @Autowired
    IDireccionClinicaService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<DireccionClinica> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<DireccionClinica> listarById(@PathVariable("id") int id){
        return service.listarById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?>guardarDireccionClinica(@RequestParam("idClinica") int idClinica, @RequestParam("idDireccion") int idDireccion){
        DireccionClinica direccionClinica = null;
        System.out.printf("idClinica: "+idClinica+", idDireccion: "+idDireccion);
        direccionClinica = service.save(idClinica, idDireccion);
        if(direccionClinica!=null){
            return new ResponseEntity<>(direccionClinica, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        String message = null;
        message = service.delete(id);
        if(message.equals("sUCCESS")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
