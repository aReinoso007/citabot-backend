package com.citabot.controller;

import com.citabot.interfaceService.ICitaService;
import com.citabot.model.Cita;
import com.citabot.model.formulario.FCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/cita")
public class CitaController {

    @Autowired
    ICitaService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Cita> listar(){
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Cita> listarById(@PathVariable("id") int id){
        return service.listarById(id);
    }

    /*Devuelve las fechas de las citas agendadas en orden por fecha */
    @GetMapping(path = "/fechas/{id}")
    public List<String> listarFechasOrdenadasPorRegistro(@PathVariable("id") int id){
        return service.citasOrdenadasFechaPorRegistro(id);
    }

    @GetMapping(path = "/query")
    public List<Cita> listarByRegistroId(@RequestParam int registro){
        return service.listarByRegistroId(registro);
    }
    /*paId = pacienteId, regId = id del registro de clinica de medico */
    @PostMapping(value = "/{paId}/{regId}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> agendar(
            @RequestParam("fechaCita") String fechaCita, @RequestParam("sintomas") String sintomas, @PathVariable(name = "paId") int paId, @PathVariable(name = "regId") int regId){
        Cita cita = new Cita();
        FCita formularioCita = new FCita();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime lcdt = LocalDateTime.parse(fechaCita);
        formularioCita.setFechaCita(lcdt);
        formularioCita.setSintomas(sintomas);
        cita = service.save(formularioCita, paId, regId);
        if(cita!=null){
            return new ResponseEntity<>(formularioCita, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestParam("estado") String estado){
        Cita citaDB = null;
        citaDB = service.listarById(id).get();
        if(citaDB!=null){
            citaDB = service.update(id, estado);
            return new ResponseEntity<>(citaDB, HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
