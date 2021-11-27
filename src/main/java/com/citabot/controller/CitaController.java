package com.citabot.controller;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/cita")
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

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> agendar(
            @RequestParam("fechaCita") Date fechaCita , @RequestParam("sintomas") String sintomas,
            @RequestParam("precio")BigDecimal precio, @RequestParam("pacienteId") int pacienteId,
            @RequestParam("registroId") int registroId, @RequestParam("hInicio") String hiInicio,
            @RequestParam("hFin") String hFin){

        Cita citaDb = new Cita();
        Cita cita = new Cita();
        citaDb.setFechaCita(fechaCita);
        citaDb.setSintomas(sintomas);
        citaDb.setPrecioConsulta(precio);

        cita = service.save(cita);
        if(cita!=null){
            return new ResponseEntity<>(cita, HttpStatus.CREATED);
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
