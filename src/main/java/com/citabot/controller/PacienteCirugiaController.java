package com.citabot.controller;

import com.citabot.interfaceService.IPacienteCirugiaService;
import com.citabot.model.PacienteCirugia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
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

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public PacienteCirugia guardarCirugia(@RequestParam("idPaciente") int idPaciente, @RequestParam("idCirugia") int idCirugia, @RequestParam("tipo") String tipo){
        PacienteCirugia pacienteCirugia = new PacienteCirugia();
        System.out.printf("idPaciente: "+idPaciente+", idCirugia: "+idCirugia);
        try{
            pacienteCirugia = service.save(idPaciente, idCirugia, tipo);
            return pacienteCirugia;
        }catch (Error error){
            System.out.printf("Error posting cirugia de paciente: ", error.getMessage());
        }
        return pacienteCirugia;
    }

}
