package com.citabot.controller;

import com.citabot.interfaceService.IDireccionClinicaService;
import com.citabot.model.DireccionClinica;
import com.citabot.model.formulario.FClinicaDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/direccion_clinica")
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

    @PostMapping()
    public ResponseEntity<?>guardarDireccionClinica(@Valid @RequestBody FClinicaDireccion formulario){
        DireccionClinica direccionClinica = null;
        direccionClinica = service.save(formulario.getClinicaId(), formulario.getDireccionId());
        if(direccionClinica!=null){
            return new ResponseEntity<>("Agregado con exito", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
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
