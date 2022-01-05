package com.citabot.controller;

import com.citabot.interfaceService.IPacienteCirugiaService;
import com.citabot.model.PacienteCirugia;
import com.citabot.model.PacientePatologia;
import com.citabot.model.formulario.FCirugia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/paciente_cirugia")
public class PacienteCirugiaController {

    @Autowired
    IPacienteCirugiaService service;

    @GetMapping(produces = "application/json")
    public List<PacienteCirugia> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    public Optional<PacienteCirugia> listarById(@PathVariable("id") int id){
        return service.listarById(id);
    }

    @PostMapping( )
    public PacienteCirugia guardarCirugia(@RequestBody FCirugia formulario){
        PacienteCirugia pacienteCirugia = new PacienteCirugia();
       // System.out.printf("idPaciente: "+idPaciente+", idCirugia: "+idCirugia);
        try{
            pacienteCirugia = service.save(formulario.getIdPaciente(), formulario.getIdCirugia(), formulario.getTipo());
            return pacienteCirugia;
        }catch (Error error){
            System.out.printf("Error posting cirugia de paciente: ", error.getMessage());
        }
        return pacienteCirugia;
    }

    @PostMapping("/update")
    public PacienteCirugia updateCirugia(@RequestBody FCirugia formulario){
        PacienteCirugia pacienteCirugia = new PacienteCirugia();
        try{
            pacienteCirugia = service.update(formulario);
            return  pacienteCirugia;
        }catch (Error error){
            System.out.printf("Error processing Update: ", error.getMessage());
        }
        return pacienteCirugia;

    }
}
