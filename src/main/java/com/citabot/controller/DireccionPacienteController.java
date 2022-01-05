package com.citabot.controller;

import com.citabot.interfaceService.IDireccionPacienteService;
import com.citabot.model.DireccionPaciente;
import com.citabot.model.PacientePatologia;
import com.citabot.model.formulario.FCirugia;
import com.citabot.model.formulario.FPDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @PostMapping( )
    public DireccionPaciente guardarDireccionPaciente(@RequestBody FPDireccion formulario){
        DireccionPaciente direccionPacienteDB = null;
       // System.out.printf("idPaciente: "+idPaciente+", idDireccion: "+idDireccion+", tipo: "+tipo);
        try {
            direccionPacienteDB = service.save(formulario.getIdDireccion(), formulario.getIdPaciente(), formulario.getTipo());
            return direccionPacienteDB;
        }catch (Error error){
            System.out.printf("Error processing Post: ", error.getMessage());
        }
        return direccionPacienteDB;
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<?> eliminarPorId(@RequestBody int id){
        String message = null;
         message = service.delete(id);
         if(message.equals("SUCCESS")){
            return new ResponseEntity<>(message, HttpStatus.OK);
         }else {
             return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
         }
    }

    @PostMapping("/update")
    public DireccionPaciente updateDireccionPaciente(@RequestBody FPDireccion formulario){

        DireccionPaciente direccionPaciente = new DireccionPaciente();

        try{
            direccionPaciente = service.update(formulario);
            return  direccionPaciente;
        }catch (Error error){
            System.out.printf("Error processing Update: ", error.getMessage());
        }
        return direccionPaciente;
    }

}
