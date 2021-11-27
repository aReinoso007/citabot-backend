package com.citabot.controller;


import com.citabot.interfaceService.IMedicoEspecialidadService;

import com.citabot.model.Especialidad;
import com.citabot.model.MedicoEspecialidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/medico_especialidad")
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

    /*Arreglar aqui, en el front ya se recupera los is por seleccion, esto es solo de prueba */
    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public MedicoEspecialidad guardarRegistro(@RequestParam("medico_id") int medico_id, @RequestParam("especialidad_id") int especialidad_id){
        MedicoEspecialidad medicoEspecialidad = new MedicoEspecialidad();

        try{
            medicoEspecialidad = service.save(medico_id, especialidad_id);
            return medicoEspecialidad;
        }catch (Error error){
            System.out.printf("Error saving Medico especialidad: ", error.getMessage());
        }
        return medicoEspecialidad;
    }


}
