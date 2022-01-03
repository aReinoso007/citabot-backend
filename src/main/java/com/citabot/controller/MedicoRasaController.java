package com.citabot.controller;

import com.citabot.Constants;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.model.Medico;
import com.citabot.model.Paciente;
import com.citabot.model.formulario.CitaD;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping("/api/private/medico")
@RequestMapping("/api/public/medico")
public class MedicoRasaController {

    @Autowired
    IMedicoService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Medico> getById(@PathVariable("id") int id){
        return service.findById(id);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Medico medico){
        Medico medicodb = null;
        medicodb = service.findById(id).get();
        if(medicodb!=null){
            service.update(id, medico);
            medicodb = service.findById(id).get();
            return new ResponseEntity<>(medicodb, HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        String meesage = null;
        meesage = service.delete(id);
        if(meesage.equals("SUCCESS")){
            return new ResponseEntity<>(meesage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(meesage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/query")
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> getByMedicoEspecialidad(@RequestParam int idEspecialidad){
        return service.Listar_medicos_especialidad(idEspecialidad);
    }

    @GetMapping(path = "/clinica/{id}")
    public List<Medico> listarMedicosPorClinica(@PathVariable("id") int id){
        return service.Listar_medicos_clinica(id);
    }


}
