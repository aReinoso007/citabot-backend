package com.citabot.controller;

import com.citabot.interfaceService.IMedicoEspecialidadService;
import com.citabot.interfaceService.IMedicoSubespecialidadService;
import com.citabot.model.Especialidad;
import com.citabot.model.MedicoEspecialidad;
import com.citabot.model.MedicoSubespecialidad;
import com.citabot.model.formulario.FSubespecialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private/medico_subespecialidad")
public class MedicoSubespecialidadController {

    @Autowired
    IMedicoSubespecialidadService service;


    @GetMapping(produces = "application/json")
    public List<MedicoSubespecialidad> listar(){
        return service.findAll();
    }


    /*Arreglar aqui, en el front ya se recupera los is por seleccion, esto es solo de prueba */
    @PostMapping()
    public ResponseEntity<?> guardarRegistro(@RequestBody FSubespecialidad formulario){
        MedicoSubespecialidad medicosubEspecialidad = new MedicoSubespecialidad();

        medicosubEspecialidad = service.save(formulario.getMedicoId(), formulario.getSubespecialidadId());
        if(medicosubEspecialidad!=null){
            return new ResponseEntity<>("Exito", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

    }

}
