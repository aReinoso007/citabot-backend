package com.citabot.controller;

import com.citabot.interfaceService.IClinicaService;
import com.citabot.model.Clinica;
import com.citabot.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/clinica")
public class ClinicaRasaController {

    @Autowired
    IClinicaService service;

    /* Funciona */
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Clinica> listar(){
        return service.listar();
    }

    /* Funciona */
    @GetMapping("/query")
    @ResponseStatus(HttpStatus.OK)
    public List<Clinica> getByNombre(@RequestParam String nombre){
        return service.listarByNombre(nombre);
    }

    @GetMapping( path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Clinica> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @GetMapping(path = "/disponibles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Clinica> getDisponiblesParaMedico(@PathVariable("id") int id){
        return service.listarClinicasDisponiblesParaMedico(id);
    }

    /*Funciona */
    @PostMapping
    public ResponseEntity<?> guardarClinica(@RequestBody Clinica clinica){
        Clinica clinicaDb = null;
        clinicaDb = service.save(clinica);
        if(clinicaDb!=null){
            return new ResponseEntity<>(clinicaDb.getClinicaId(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") int id){
        return service.delete(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClinica(@PathVariable("id") int id, @RequestBody Clinica clinica){
        Clinica clinicaDB = null;
        clinicaDB = service.findById(id).get();
        if(clinicaDB!=null){
            clinicaDB.setNombreClinica(clinica.getNombreClinica());
            service.update(clinicaDB);
            return new ResponseEntity<>(clinicaDB, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/medico_clinica")
    @ResponseStatus(HttpStatus.OK)
    public List<Clinica> listarClinicasMedico(@RequestParam int idMedico){
        return service.listarPorMedico(idMedico);
    }

}

