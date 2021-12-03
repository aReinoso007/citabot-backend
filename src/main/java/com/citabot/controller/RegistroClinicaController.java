package com.citabot.controller;

import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/registro_clinica")
public class RegistroClinicaController {

    @Autowired
    IRegistroClinicaService service;

    @GetMapping(produces = "application/json")
    public List<RegistroClinica> listar(){
        return service.findAll();
    }

    @GetMapping(value = "/buscar/{medId}/{cliId}", produces = "application/json")
    public Optional<Integer> listarByCliYMedico(@PathVariable("cliId") int cliId, @PathVariable("medId") int medId){
        return service.finByClinicaAndMedico(cliId, medId);
    }

    @GetMapping(path = "/{id}")
    public Optional<RegistroClinica> listarById(@PathVariable("id") int id){
        return service.findById(id);
    }

    /*Arreglar aqui, en el front ya se recupera los is por seleccion, esto es solo de prueba */
    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RegistroClinica guardarRegistro(@RequestParam("idCli") int idCli, @RequestParam("idMed") int idMed){
        RegistroClinica registroClinica = new RegistroClinica();
        System.out.printf("idClinica: "+idCli+", idMedico: "+idMed);
        try{
            registroClinica = service.save(idCli, idMed);
            return registroClinica;
        }catch (Error error){
            System.out.printf("Error saving Registro: ", error.getMessage());
        }
        return registroClinica;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable("id") int id){
        String message = null;
        message = service.delete(id);
        if(message.equals("SUCCESS")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }


}
