package com.citabot.controller;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Paciente> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Paciente getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    /*Usa el form-url-enconded */
    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password){
        Paciente pacienteDb = null;
        pacienteDb = service.buscarPorEmailYContrasena(email, password);
        if(pacienteDb!=null){
            return new ResponseEntity<>(pacienteDb, HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    /*Formato de fecha: anio-mes-dia */
    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody Paciente p){
        System.out.printf("Paciente a registrar: ", p);
        Paciente pacientedb = null;
        String message = "Paciente ya registrado con email: "+ p.getEmail();
        if(service.findByEmail(p.getEmail())==null){
            pacientedb = service.save(p);
            return new ResponseEntity<>(pacientedb, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
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
