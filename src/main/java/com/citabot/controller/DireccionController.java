package com.citabot.controller;

import com.citabot.interfaceService.IDireccionService;
import com.citabot.model.Direccion;
import com.citabot.model.Enfermedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/direccion")
public class DireccionController {

    @Autowired
    IDireccionService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Direccion> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Direccion> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> guardarDireccion(@RequestBody Direccion direccion){
        Direccion direccionDB = service.save(direccion);
        if(direccionDB!=null){
            return new ResponseEntity<>(direccionDB.getDireccionId(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Error",HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/update")
    public Direccion updateDireccion(@RequestBody Direccion direccion){
        Direccion direccion1 = new Direccion();
        try{
            direccion1 = service.update(direccion);
            return  direccion1;
        }catch (Error error){
            System.out.printf("Error processing Update: ", error.getMessage());
        }
        return direccion1;
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteById(@RequestBody int id){
        String message = null;
        message=service.delete(id);
        if(message.equals("SUCCESS")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
