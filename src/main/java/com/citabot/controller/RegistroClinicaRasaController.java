package com.citabot.controller;

import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.model.RegistroClinica;
import com.citabot.model.formulario.FRegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/registro_clinica")
public class RegistroClinicaRasaController {

    @Autowired
    IRegistroClinicaService service;

    @GetMapping(produces = "application/json")
    public List<RegistroClinica> listar(){
        return service.findAll();
    }
    /*Retorna el id del registro por la clinica y el id del medico */
    @GetMapping(value = "/buscar/{medId}/{cliId}")
    public Optional<Integer> listarByCliYMedico(@PathVariable("cliId") int cliId, @PathVariable("medId") int medId){
        return service.finByClinicaAndMedico(cliId, medId);
    }

    @GetMapping(value = "/buscar2/{medId}/{cliId}")
    public Integer listarporCliYMedico(@PathVariable("cliId") int cliId, @PathVariable("medId") int medId){
        return service.getByClinicaYMedico(cliId, medId);
    }

    @GetMapping(path = "/{id}")
    public Optional<RegistroClinica> listarById(@PathVariable("id") int id){
        return service.findById(id);
    }

    /*Arreglar aqui, en el front ya se recupera los is por seleccion, esto es solo de prueba */
    @PostMapping()
    public ResponseEntity<?> guardarRegistro(@Valid @RequestBody FRegistroClinica formulario){
        RegistroClinica registroClinica = service.save(formulario.getClinicaId(), formulario.getMedicoId());
        if(registroClinica!=null){
            return new ResponseEntity<>("Registro Exitoso",HttpStatus.CREATED);
        }else{
            return new ResponseEntity("Algo salio mal",HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable("id") int id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
