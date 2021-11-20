package com.citabot.controller;

import com.citabot.interfaceService.ICirugiaService;
import com.citabot.model.Cirugia;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Cirugia> listar(){
        return service.listar();
    }

    @GetMapping("/query")
    public Optional<Cirugia> getByDate(@RequestParam Date date){
        return service.findByDate(date);
    }

    @GetMapping(path = "/{id}")
    public Optional<Cirugia> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public Cirugia guardarCirugia(@RequestBody Cirugia cirugia){
        return service.save(cirugia);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        boolean ok = service.delete(id);
        if(ok){
            return "Success";
        }else {
            return "Failed";
        }
    }

}
