package com.citabot.controller;

import com.citabot.interfaceService.IDireccionPacienteService;
import com.citabot.model.DireccionPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/paciente_direccion")
public class DireccionPacienteController {

    @Autowired
    IDireccionPacienteService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<DireccionPaciente> listar(){
        return service.listar();
    }

    @GetMapping("/query")
    @ResponseStatus(HttpStatus.OK)
    public Optional<DireccionPaciente> listarByPacienteId(@RequestParam int paciente){
        return service.listarByPacienteId(paciente);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<DireccionPaciente> listarById(@PathVariable("id") int id){
        return service.listarById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> guardarDireccionPaciente(@RequestParam("idPaciente") int idPaciente,@RequestParam("idDireccion") int idDireccion, @RequestParam("tipo") String tipo){
        DireccionPaciente direccionPacienteDB = null;
        System.out.printf("idPaciente: "+idPaciente+", idDireccion: "+idDireccion+", tipo: "+tipo);
        direccionPacienteDB = service.save(idDireccion, idPaciente, tipo);
        if(direccionPacienteDB!=null){
            return new ResponseEntity<>(direccionPacienteDB, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") int id){
        String message = null;
         message = service.delete(id);
         if(message.equals("SUCCESS")){
            return new ResponseEntity<>(message, HttpStatus.OK);
         }else {
             return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
         }
    }

}
