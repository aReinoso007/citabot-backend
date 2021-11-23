package com.citabot.controller;

import com.citabot.interfaceService.IDireccionClinicaService;
import com.citabot.model.DireccionClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/direccion_clinica")
public class DireccionClinicaController {

    @Autowired
    IDireccionClinicaService service;

    @GetMapping(produces = "application/json")
    public List<DireccionClinica> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    public Optional<DireccionClinica> listarById(@PathVariable("id") int id){
        return service.listarById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public DireccionClinica guardarDireccionClinica(@RequestParam("idClinica") int idClinica, @RequestParam("idDireccion") int idDireccion){
        DireccionClinica direccionClinica = new DireccionClinica();
        System.out.printf("idClinica: "+idClinica+", idDireccion: "+idDireccion);
        try{
            direccionClinica = service.save(idClinica, idDireccion);
            return direccionClinica;
        }catch (Error error){
            System.out.printf("Error posting request:  ", error.getMessage());
        }
        return direccionClinica;
    }

}
