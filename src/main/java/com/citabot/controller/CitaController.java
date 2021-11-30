package com.citabot.controller;

import com.citabot.interfaceService.ICitaService;
import com.citabot.model.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping(path = "/query")
    public List<Cita> listarByRegistroId(@RequestParam int registro){
        return service.listarByRegistroId(registro);
    }

    @PostMapping(value = "/{paId}/{regId}")
    public ResponseEntity<?> agendar(
            @RequestBody Cita cita, @PathVariable(name = "paId") int paId, @PathVariable(name = "regId") int regId){
        System.out.printf("paId: "+paId+", regId: "+regId+"    ");
        cita = service.save(cita, paId, regId);
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
