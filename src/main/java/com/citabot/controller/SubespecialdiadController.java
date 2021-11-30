package com.citabot.controller;



import com.citabot.interfaceService.ISubespecialidadService;

import com.citabot.model.Subespecialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/subespecialidad")


public class SubespecialdiadController {

    @Autowired
    ISubespecialidadService service;



    @GetMapping(produces = "application/json")
    public List<Subespecialidad> listar(){
        return service.listar();
    }

    @GetMapping("/query")
    public List<Subespecialidad> getByNombre(@RequestParam String nombre){
        return service.listarByNombre(nombre);
    }

    @GetMapping(path = "/{id}")
    Optional<Subespecialidad> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Subespecialidad guardarSubespecialidad(@RequestParam("nombre") String nombre, @RequestParam("especialidad_id") int especialidad_id){
        Subespecialidad subespecialidad = new Subespecialidad();

        try{
            subespecialidad=service.save(nombre,especialidad_id);
            return subespecialidad;
        }catch (Error error){
            System.out.printf("Error posting subespecialdad: ", error.getMessage());
        }
        return subespecialidad;
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        String resp = service.delete(id);
        return resp;
    }


}
