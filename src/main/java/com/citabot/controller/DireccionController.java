package com.citabot.controller;

import com.citabot.interfaceService.IDireccionService;
import com.citabot.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    IDireccionService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Direccion> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    public Optional<Direccion> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public Direccion guardarDireccion(@RequestBody Direccion direccion){
        return  service.save(direccion);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        return service.delete(id);
    }

}
