package com.citabot.controller;


import com.citabot.interfaceService.IMedicoEspecialidadService;

import com.citabot.model.Especialidad;
import com.citabot.model.MedicoEspecialidad;

import com.citabot.model.formulario.FMedicoEspecialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private/medico_especialidad")
public class MedicoEspecialidadController {

    @Autowired
    IMedicoEspecialidadService service;

    @GetMapping(produces = "application/json")
    public List<MedicoEspecialidad> listar(){
        return service.findAll();
    }


    @GetMapping(path = "/{id}")
    public Optional<MedicoEspecialidad> listarById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @GetMapping("/registro/{medId}/{espId}")
    public Integer getRegistroId(@PathVariable("medId") int medId, @PathVariable("espId") int espId){
        return service.getRegistroId(medId, espId);
    }

    /*Arreglar aqui, en el front ya se recupera los is por seleccion, esto es solo de prueba */
    @PostMapping()
    public ResponseEntity<?> guardarRegistro(@RequestBody FMedicoEspecialidad form){
        MedicoEspecialidad medicoEspecialidad = service.save(form.getMedicoId(), form.getEspecialidadId());
        if(medicoEspecialidad!=null){
            return new ResponseEntity<>("Exito", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteRegistroEspecialidad(@RequestBody int id){
        service.deleteRegistroEspecialidad(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
