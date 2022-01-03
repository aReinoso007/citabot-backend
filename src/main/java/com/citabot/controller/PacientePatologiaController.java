package com.citabot.controller;


import com.citabot.interfaceService.IPacientePatologiaService;
import com.citabot.model.Enfermedad;
import com.citabot.model.PacientePatologia;
import com.citabot.model.formulario.FPatologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private/paciente_patologia")
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

    @PostMapping( )
    public PacientePatologia guardarPatologia(@RequestBody FPatologia formulario){
        PacientePatologia pacientePatologia = new PacientePatologia();
        //System.out.printf("id paciente: "+idPaciente +", idEnfermedad: "+idEnfermedad+", Tipo: "+tipo);
        try{
            pacientePatologia = service.save(formulario.getTipo(), formulario.getIdPaciente(), formulario.getIdEnfermedad());
            return  pacientePatologia;
        }catch (Error error){
            System.out.printf("Error processing Post: ", error.getMessage());
        }
        return pacientePatologia;
    }

    @PutMapping("/update")
    public PacientePatologia updatePatologia(@RequestBody PacientePatologia patologiaPaciente){
        PacientePatologia pacientePatologia = new PacientePatologia();
        //System.out.printf("id paciente: "+idPaciente +", idEnfermedad: "+idEnfermedad+", Tipo: "+tipo);
        try{
            pacientePatologia = service.save(patologiaPaciente.getTipo(), patologiaPaciente.getPaciente().getUsuarioId(), patologiaPaciente.getEnfermedad().getEnfermedadId());
            return  pacientePatologia;
        }catch (Error error){
            System.out.printf("Error processing Update: ", error.getMessage());
        }
        return pacientePatologia;
    }


}
