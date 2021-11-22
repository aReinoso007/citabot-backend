package com.citabot.service;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaces.ICirugia;
import com.citabot.interfaces.IEnfermedad;
import com.citabot.interfaces.IPaciente;
import com.citabot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
        System.out.printf("paciente ID: ", pacId);
        System.out.printf("Tipo: ", tipo);
        System.out.printf("cirugia ID: ", cirugiaId);
        PacienteCirugia pacienteCirugia = new PacienteCirugia();

        Cirugia cirugia = new Cirugia();
        Paciente paciente = new Paciente();
        /*Para actualizar el updated_at */

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        try{
                cirugia = cirugiaData.findById(3).get();
                System.out.printf("cirugia recuperada: ", cirugia);
                paciente = data.findPacienteByUsuarioId(2).get();
                System.out.printf("paciente recuperada: ", paciente);

                /*Actualizo al registro de paciente_cirugia */
                pacienteCirugia.setCirugia(cirugia);
                pacienteCirugia.setPaciente(paciente);
                pacienteCirugia.setTipo("personal");

                /*Agrego a la lista de paciente_cirugia */
                paciente.getPacienteCirugias().add(pacienteCirugia);
                paciente.setUpdatedAt(ts);
                data.save(paciente);

        }catch (Error error){
            System.out.printf("ERROR ADDING SURGERY TO PATIENT: ", error.getMessage());
        }
        return paciente;
    }
}
