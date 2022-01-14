package com.citabot.controller;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IClinicaService;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaceService.IMedicoEspecialidadService;
import com.citabot.interfaces.MailService;
import com.citabot.model.Cita;
import com.citabot.model.Clinica;
import com.citabot.model.Medico;
import com.citabot.model.MedicoEspecialidad;
import com.citabot.model.clases.Mail;
import com.citabot.model.formulario.CitaD;
import com.citabot.model.formulario.interfaces.CitaConstl;
import com.citabot.model.formulario.FCita;
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
@RequestMapping("/api/public/cita")
@Slf4j
public class CitaRasaController {

    @Autowired
    ICitaService service;

    @Autowired
    IClinicaService serviceClinica;

    @Autowired
    IMedicoEspecialidadService serviceMedEsp;

    @Autowired
    IMedicoService serviceMedico;

    @Autowired
    MailService serviceMail;

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



/*    @GetMapping(path = "/email")
    @ResponseStatus(HttpStatus.OK)
    public String enviaremail(){
        Mail mail = new Mail();
        mail.setMailFrom("citadoc@gmail.com");
        mail.setMailTo("zhirzhancris12@hotmail.com");
        mail.setMailSubject("Agendamiento de cita médica");

        String content="Usted ha agendado la cita médica con los siguientes datos:"+"\n" +
                "Especialidad: "+"Odontología "+"\n" +" Doctor/a: "+"Luis Felipe "+"\n"+ " Clínica: "+
                "Humanitaria "+"\n" +"Fecha: "+"2022-01-13 13:00:00";
        mail.setMailContent(content);

*//*        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        MailService mailService = (MailService) ctx.getBean("mailService");*//*
        serviceMail.sendEmail(mail);

        return "email";
    }*/


    /*Devuelve las fechas de las citas agendadas en orden por fecha */
    @GetMapping(path = "/fechas/{id}")
    public List<String> listarFechasOrdenadasPorRegistro(@PathVariable("id") int id){
        return service.citasOrdenadasFechaPorRegistro(id);
    }

    @GetMapping(path = "/citas/{id}")
    public List<CitaD> listarCitasPorPaciente(@PathVariable("id") int id){
        List<CitaConstl> list = new ArrayList<>();
        List<CitaD> listC = new ArrayList<>();
        Clinica clinica = new Clinica();
        Medico medico = new Medico();
        MedicoEspecialidad mesp = new MedicoEspecialidad();
        CitaD citad = new CitaD();
        list =service.Listar_citas_paciente(id);
        for (CitaConstl cita : list) {

            citad.setCitaId(cita.getcitaId());
            citad.setFechaCita(cita.getfechaCita().toString());
            citad.setSintomas(cita.getsintomas());
            //Recupera la clinica
            clinica = serviceClinica.findById(cita.getclinicaId()).get();
            medico = serviceMedico.findById(cita.getmedicoId()).get();
            citad.setClinica(clinica.getNombreClinica());
            citad.setMedico(medico.getNombre()+' '+medico.getApellido());

            listC.add(citad);
            citad = new CitaD();

        }
        return listC;
    }

    @GetMapping(path = "/cita_detalle/{id}")
    public CitaD getCitaDetalle(@PathVariable("id") int id){
        List<CitaConstl> list = new ArrayList<>();
        List<CitaD> listC = new ArrayList<>();
        Clinica clinica = new Clinica();
        Medico medico = new Medico();
        MedicoEspecialidad mesp = new MedicoEspecialidad();
        CitaD citad = new CitaD();
        list =service.obtenerCitaDetalle(id);
        for (CitaConstl cita : list) {

            citad.setCitaId(cita.getcitaId());
            citad.setFechaCita(cita.getfechaCita().toString());
            citad.setSintomas(cita.getsintomas());
            //Recupera la clinica
            clinica = serviceClinica.findById(cita.getclinicaId()).get();
            medico = serviceMedico.findById(cita.getmedicoId()).get();
            citad.setClinica(clinica.getNombreClinica());
            citad.setMedico(medico.getNombre()+' '+medico.getApellido());

            //listC.add(citad);
           // citad = new CitaD();

        }
        return citad;
    }

    @GetMapping(path = "/query")
    public List<Cita> listarByRegistroId(@RequestParam int registro){
        return service.listarByRegistroId(registro);
    }

    /*paId = pacienteId, regId = id del registro de clinica de medico */
    @PostMapping(value = "/{paId}/{regId}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> agendar(
            @RequestParam("fechaCita") String fechaCita, @RequestParam("sintomas") String sintomas, @PathVariable(name = "paId") int paId, @PathVariable(name = "regId") int regId){
        log.info("fecha ",fechaCita);
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

