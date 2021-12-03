package com.citabot.controller;

import com.citabot.interfaceService.IHorarioService;
import com.citabot.model.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    /*Me muestra los dias que trabaja */
    @GetMapping(path = "/dias/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> listarDias(@PathVariable("id") int id){
        return service.listarDiasDelHorarioPorRegistro(id);
    }

    /*Me devuelve el dia, inicio, fin, intervalo ej
    *   "monday,07:30:00,12:00:00,00:30:00",
        "tuesday,07:30:00,12:00:00,00:30:00",
        "tuesday,13:30:00,18:00:00,00:30:00"
        * esto me sirve para obtener los intervaos de tiempo del horario*/
    @GetMapping(path = "/fechas_ordenadas/{id}")
    public List<String> listarHorarioOrdenado(@PathVariable("id") int id){
        return service.horariosRegistroOrdenado(id);
    }

    /*Devuelve  */
    @GetMapping(path = "/fechas/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<LocalDateTime> listarFechasDisponibles(@PathVariable("id") int id){
        return  service.listarFechasDisponibles(id);
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
