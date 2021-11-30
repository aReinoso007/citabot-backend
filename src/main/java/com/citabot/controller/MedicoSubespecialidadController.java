package com.citabot.controller;

import com.citabot.interfaceService.IMedicoEspecialidadService;
import com.citabot.interfaceService.IMedicoSubespecialidadService;
import com.citabot.model.Especialidad;
import com.citabot.model.MedicoEspecialidad;
import com.citabot.model.MedicoSubespecialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/medico_subespecialidad")
public class MedicoSubespecialidadController {

    @Autowired
    IMedicoSubespecialidadService service;


    @GetMapping(produces = "application/json")
    public List<MedicoSubespecialidad> listar(){
        return service.findAll();
    }


    /*Arreglar aqui, en el front ya se recupera los is por seleccion, esto es solo de prueba */
    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public MedicoSubespecialidad guardarRegistro(@RequestParam("medico_id") int medico_id, @RequestParam("subespecialidad_id") int subespecialidad_id){
        MedicoSubespecialidad medicosubEspecialidad = new MedicoSubespecialidad();

        try{
            medicosubEspecialidad = service.save(medico_id, subespecialidad_id);
            return medicosubEspecialidad;
        }catch (Error error){
            System.out.printf("Error saving Medico subespecialidad: ", error.getMessage());
        }
        return medicosubEspecialidad;
    }



}
