package com.citabot.controller;

import com.citabot.interfaceService.IMedicoService;
import com.citabot.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/medico")
public class MedicoController {

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

    /*Usa el form-url-enconded */
    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password){
        Medico medicodb = null;
        medicodb = service.findByEmailAndContrasena(email, password);
        if(medicodb!=null){
            return new ResponseEntity<>(medicodb, HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody Medico medico){
        Medico medicodb = null;
        String message = "Medico ya registrado con email: "+medico.getEmail();
        if(service.findByEmail(medico.getEmail())==null){
            medicodb = service.save(medico);
            return new ResponseEntity<>(medicodb, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

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

}
