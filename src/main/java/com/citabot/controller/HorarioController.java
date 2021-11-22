package com.citabot.controller;

import com.citabot.interfaceService.IHorarioService;
import com.citabot.model.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    IHorarioService service;

    @GetMapping(produces = "application/json")
    public List<Horario> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    public Optional<Horario> listarById(@PathVariable("id") int id){
        return service.listarById(id);
    }

    /*Este post se realiza enviando el id del registro por la url */
    @PostMapping(path = "/guardar/{id}")
    public Horario guardarHorario(@PathVariable("id") int id, @RequestParam String duracion, @RequestParam String dia, @RequestParam String inicio, @RequestParam String fin){
        Horario h = new Horario();
        System.out.printf("Duracion: "+duracion+", dia: "+dia+", inicio: "+inicio+", fin: "+fin+". id: "+id);
        try{
            h = service.save(duracion,dia, inicio, fin, id);
        }catch (Error error){
            System.out.printf("Error processing post request: ", error.getMessage());
        }
        return h;
    }



}
