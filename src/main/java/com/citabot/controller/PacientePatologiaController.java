package com.citabot.controller;


import com.citabot.interfaceService.IPacientePatologiaService;
import com.citabot.model.PacientePatologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/paciente_patologia")
public class PacientePatologiaController {

    @Autowired
    IPacientePatologiaService service;

    @GetMapping(produces = "application/json")
    public List<PacientePatologia> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    public Optional<PacientePatologia> listarById(@PathVariable("id") int id){
        return service.listarById(id);
    }

    /*Listar usando el id del paciente */
    @GetMapping("/query")
    public List<PacientePatologia> listarByPacienteId(@RequestParam int paciente){
        return service.listarByPacienteId(paciente);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        return service.delete(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public PacientePatologia guardarPatologia(@RequestParam("idPaciente") int idPaciente, @RequestParam("idEnfermedad") int idEnfermedad, @RequestParam("tipo") String tipo){
        PacientePatologia pacientePatologia = new PacientePatologia();
        System.out.printf("id paciente: "+idPaciente +", idEnfermedad: "+idEnfermedad+", Tipo: "+tipo);
        try{
            pacientePatologia = service.save(tipo, idPaciente, idEnfermedad);
            return  pacientePatologia;
        }catch (Error error){
            System.out.printf("Error processing Post: ", error.getMessage());
        }
        return pacientePatologia;
    }


}
