package com.citabot.controller;

import com.citabot.interfaceService.IEnfermedadService;
import com.citabot.model.Enfermedad;
import com.citabot.model.PacientePatologia;
import com.citabot.model.formulario.FPatologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private/enfermedad")
public class EnfermedadController {

    @Autowired
    IEnfermedadService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Enfermedad> listar(){
        return service.listar();
    }

    @GetMapping("/query")
    @ResponseStatus(HttpStatus.OK)
    public List<Enfermedad> getByNombre(@RequestParam String nombre){
        return service.listarByNombre(nombre);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Optional<Enfermedad> getById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> guardarEnfermedad(@RequestBody Enfermedad enfermedad){
        Enfermedad enfermedadDB = null;
        System.out.println("Enfermedad...... "+enfermedad);
        enfermedadDB = service.save(enfermedad);
        if(enfermedadDB!=null){
            return new ResponseEntity<>(enfermedadDB.getEnfermedadId(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        String message =null;
        message = service.delete(id);
        if(message.equals("SUCCESS")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    /*Para actualizar nombre, tipo y descripcion */
    @PostMapping("/update")
    public Enfermedad updateEnfermedad(@RequestBody Enfermedad enfermedad){
        Enfermedad enfermedad1 = new Enfermedad();

        //System.out.printf("id paciente: "+idPaciente +", idEnfermedad: "+idEnfermedad+", Tipo: "+tipo);
        try{
            enfermedad1 = service.update(enfermedad);
            return  enfermedad1;
        }catch (Error error){
            System.out.printf("Error processing Update: ", error.getMessage());
        }
        return enfermedad1;
    }
}
