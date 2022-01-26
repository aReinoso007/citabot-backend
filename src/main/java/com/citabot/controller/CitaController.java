package com.citabot.controller;

import com.citabot.interfaceService.*;
import com.citabot.model.*;
import com.citabot.model.formulario.CitaD;
import com.citabot.model.formulario.FCitaDMedico;
import com.citabot.model.formulario.interfaces.CitaConstl;
import com.citabot.model.formulario.FCita;
import com.citabot.model.formulario.interfaces.CitaDets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/private/cita")
@Slf4j
public class CitaController {

    @Autowired
    ICitaService service;

    @Autowired
    IClinicaService serviceClinica;
    @Autowired
    IPacienteService pacienteService;

    @Autowired
    IMedicoEspecialidadService serviceMedEsp;

    @Autowired
    IMedicoService serviceMedico;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Cita> listar() {
        return service.listar();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Cita> listarById(@PathVariable("id") int id) {
        return service.listarById(id);
    }

    /* Devuelve las fechas de las citas agendadas en orden por fecha */
    @GetMapping(path = "/fechas/{id}")
    public List<String> listarFechasOrdenadasPorRegistro(@PathVariable("id") int id) {
        return service.citasOrdenadasFechaPorRegistro(id);
    }

    @GetMapping(path = "historial/{id}")
    public List<FCitaDMedico> getHistorialCitasMedico(@PathVariable("id") int id) {
        List<CitaDets> list = service.getHistorialCitasDeMedico(id);
        List<FCitaDMedico> listaFinal = new ArrayList<>();
        Clinica clinica = new Clinica();
        Paciente paciente = new Paciente();
        FCitaDMedico citaD = new FCitaDMedico();
        for(CitaDets cita: list){
            citaD.setCitaId(cita.getCitaId());
            citaD.setFechaCita(cita.getfechaCita().toString());
            citaD.setSintomas(cita.getsintomas());
            citaD.setEstado(cita.getEstado());
            clinica = serviceClinica.findById(cita.getclinicaId()).get();
            paciente = pacienteService.findById(cita.getpacienteId());
            citaD.setClinica(clinica.getNombreClinica());
            citaD.setPaciente(paciente.getNombre()+" "+paciente.getApellido());
            listaFinal.add(citaD);
            citaD = new FCitaDMedico();
        }
        return listaFinal;
    }

    @GetMapping(path = "/hoy/{id}")
    public List<FCitaDMedico> getTodayCitas(@PathVariable("id") int id) {
        List<CitaDets> list = service.getCitasDeHoy(id);
        List<FCitaDMedico> listaFinal = new ArrayList<>();
        Clinica clinica = new Clinica();
        Paciente paciente = new Paciente();
        FCitaDMedico citaD = new FCitaDMedico();
        for(CitaDets cita: list){
            citaD.setCitaId(cita.getCitaId());
            citaD.setFechaCita(cita.getfechaCita().toString());
            citaD.setSintomas(cita.getsintomas());
            citaD.setEstado(cita.getEstado());
            clinica = serviceClinica.findById(cita.getclinicaId()).get();
            paciente = pacienteService.findById(cita.getpacienteId());
            citaD.setClinica(clinica.getNombreClinica());
            citaD.setPaciente(paciente.getNombre()+" "+paciente.getApellido());
            listaFinal.add(citaD);
            citaD = new FCitaDMedico();
        }
        return listaFinal;
    }

    @GetMapping(path = "/citas/{id}")
    public List<CitaD> listarCitasPorPaciente(@PathVariable("id") int id) {
        List<CitaConstl> list = new ArrayList<>();
        List<CitaD> listC = new ArrayList<>();
        Clinica clinica = new Clinica();
        Medico medico = new Medico();
        MedicoEspecialidad mesp = new MedicoEspecialidad();
        CitaD citad = new CitaD();
        list = service.Listar_citas_paciente(id);

        for (CitaConstl cita : list) {

            citad.setCitaId(cita.getcitaId());
            citad.setFechaCita(cita.getfechaCita().toString());
            citad.setSintomas(cita.getsintomas());
            // Recupera la clinica
            clinica = serviceClinica.findById(cita.getclinicaId()).get();
            medico = serviceMedico.findById(cita.getmedicoId()).get();
            citad.setClinica(clinica.getNombreClinica());
            citad.setMedico(medico.getNombre() + ' ' + medico.getApellido());

            listC.add(citad);
            citad = new CitaD();

        }

        return listC;
    }

    @GetMapping(path = "/query")
    public List<Cita> listarByRegistroId(@RequestParam int registro) {
        return service.listarByRegistroId(registro);
    }

    /* paId = pacienteId, regId = id del registro de clinica de medico */
    @PostMapping(value = "/{paId}/{regId}", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public ResponseEntity<?> agendar(
            @RequestParam("fechaCita") String fechaCita, @RequestParam("sintomas") String sintomas,
            @PathVariable(name = "paId") int paId, @PathVariable(name = "regId") int regId){
        log.info("fecha ", fechaCita);
        Cita cita = new Cita();
        FCita formularioCita = new FCita();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime lcdt = LocalDateTime.parse(fechaCita);
        formularioCita.setFechaCita(lcdt);
        formularioCita.setSintomas(sintomas);
        cita = service.save(formularioCita, paId, regId);
        if (cita != null) {
            return new ResponseEntity<>(formularioCita, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

    }

/*    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestParam("estado") String estado) {
        Cita citaDB = null;
        citaDB = service.listarById(id).get();
        if (citaDB != null) {
            citaDB = service.update(id, estado);
            return new ResponseEntity<>(citaDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }*/

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Cita cita) {
        Cita citaDB = null;
        String message="SUCCESS";
        citaDB = service.listarById(cita.getCitaId()).get();
        if (citaDB != null) {
            citaDB = service.update(cita.getCitaId(), cita.getEstado());
            return new ResponseEntity<>(message,HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }



/*    @PostMapping(value="/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestParam("estado") String estado) {
        Cita citaDB = null;
        System.out.println("ESTAD0: "+estado);
        citaDB = service.listarById(id).get();
        if (citaDB != null) {
            citaDB = service.update(id, estado);
            return new ResponseEntity<>(citaDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }*/
}
