package com.citabot.controller;

import com.citabot.interfaceService.ICirugiaService;
import com.citabot.model.Cirugia;
import com.citabot.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteById(@RequestBody int id){
        boolean ok = service.delete(id);
        String message = "SUCCESS";
        if(ok){
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message = "FAILED";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    public Cirugia updateCirugia(@RequestBody Cirugia cirugia ){
        Cirugia cirugia1 = new Cirugia();
        try{
            cirugia1 = service.update(cirugia);
            return  cirugia1;
        }catch (Error error){
            System.out.printf("Error processing Update: ", error.getMessage());
        }
        return cirugia1;
    }



}
