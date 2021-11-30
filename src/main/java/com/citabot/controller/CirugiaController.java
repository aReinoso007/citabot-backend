package com.citabot.controller;

import com.citabot.interfaceService.ICirugiaService;
import com.citabot.model.Cirugia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/cirugia")
public class CirugiaController {

    @Autowired
    ICirugiaService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Cirugia> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Cirugia> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> guardarCirugia(@RequestBody Cirugia cirugia){
        Cirugia cirugiaDB = null;
        cirugiaDB = service.save(cirugia);
        if(cirugiaDB!=null){
            return new ResponseEntity<>(cirugiaDB, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        boolean ok = service.delete(id);
        String message = "SUCCESS";
        if(ok){
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message = "FAILED";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}
