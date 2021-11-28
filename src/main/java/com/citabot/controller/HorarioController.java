package com.citabot.controller;

import com.citabot.interfaceService.IHorarioService;
import com.citabot.model.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.OK)
    public List<Horario> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Horario> listarById(@PathVariable("id") int id){
        return service.listarById(id);
    }

    @GetMapping(path = "/query")
    @ResponseStatus(HttpStatus.OK)
    public List<Horario> listarByRegistro(@RequestParam int registro){
        return service.listarByClinicaRegistro(registro);
    }

    /*Este post se realiza enviando el id del registro por la url */
    @PostMapping(path = "/guardar/{id}")
    public Horario guardarHorario(@PathVariable("id") int id, @RequestBody Horario horario){
        Horario h = new Horario();
        try{
            h = service.save(id, horario);
        }catch (Error error){
            System.out.printf("Error processing post request: ", error.getMessage());
        }
        return h;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable("id") int id){
        String message = null;
        message = service.delete(id);
        if(message.equals("SUCCESS")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }


}
