package com.citabot.controller;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.model.Paciente;
import com.citabot.model.PacientePatologia;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Paciente> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public Paciente save(@RequestBody Paciente p){
        return service.save(p);
    }

    @PutMapping(value = "/add_patologia/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> addPacientePatologia(@PathVariable(value = "id") int id,@RequestParam("tipo") String tipo,@RequestParam("idEnf") int idEnf){
        Paciente paciente = null;
        paciente = service.findById(id).get();
        if(paciente != null){
            service.addPatologia(tipo, idEnf, id);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/add_cirugia/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> addPacienteCirugia(@PathVariable(value = "id") int id, @RequestParam("tipo") String tipo, @RequestParam("idCir") int idCir){
        Paciente paciente = null;
        paciente = service.findById(id).get();
        if(paciente !=null){
            service.addCirugia(tipo, idCir, id);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }


}
