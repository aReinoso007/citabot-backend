package com.citabot.service;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaces.ICirugia;
import com.citabot.interfaces.IEnfermedad;
import com.citabot.interfaces.IPaciente;
import com.citabot.interfaces.IPacientePatologia;
import com.citabot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPaciente data;
    @Autowired
    private IEnfermedad enfermedadData;
    @Autowired
    private ICirugia cirugiaData;

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> listar() {
        return (List<Paciente>) data.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> listarByNombre(String n) {
        return data.findPacientesByNombre(n);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> findById(int id) {
        return data.findPacienteByUsuarioId(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return data.save(paciente);
    }

    @Override
    public Paciente edit(Paciente paciente) {
        return data.save(paciente);
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try{
            data.deleteById(id);
        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> listarByNombreYApellido(String n) {
        String[] nombre = n.split(" ");
        return (Optional<Paciente>) data.findPacientesByNombreOrApellido(nombre[0].toString(), nombre[1].toString());
    }

    @Override
    public Paciente addPatologia(String tipo, int enfermedadId, int pacId) {
        PacientePatologia pacientePatologia = new PacientePatologia();
        Enfermedad enfermedad = new Enfermedad();
        Paciente p = new Paciente();
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        try{
            if(enfermedadData.existsById(enfermedadId)){
                enfermedad = enfermedadData.findById(enfermedadId).get();
                p = data.findPacienteByUsuarioId(pacId).get();
                pacientePatologia.setEnfermedad(enfermedad);
                pacientePatologia.setPaciente(p);
                pacientePatologia.setTipo(tipo);
                p.getPacientePatologias().add(pacientePatologia);
                p.setUpdatedAt(ts);
                data.save(p);
            }else{
                return null;
            }

        }catch (Error error){
            System.out.printf("ERROR ADDING PATOLOGY TO PATIENT: ", error.getMessage());
        }

        return p;
    }

    @Override
    public Paciente addCirugia(String tipo, int cirugiaId, int pacId) {
        PacienteCirugia pacienteCirugia = new PacienteCirugia();
        Cirugia cirugia = new Cirugia();
        Paciente paciente = new Paciente();
        /*Para actualizar el updated_at */
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        if(cirugiaData.existsById(cirugiaId) && data.existsById(pacId)){
            try{
                cirugia = cirugiaData.findById(cirugiaId).get();
                paciente = data.findPacienteByUsuarioId(pacId).get();
                /*Actualizo al registro de paciente_cirugia */
                pacienteCirugia.setCirugia(cirugia);
                pacienteCirugia.setPaciente(paciente);
                pacienteCirugia.setTipo(tipo);
                /*Agrego a la lista de paciente_cirugia */
                paciente.getPacienteCirugias().add(pacienteCirugia);
                paciente.setUpdatedAt(ts);
                data.save(paciente);
            }catch (Error error){
                System.out.printf("ERROR ADDING SURGERY TO PATIENT: ", error.getMessage());
            }
        }else {
            return null;
        }
        return paciente;
    }
}
