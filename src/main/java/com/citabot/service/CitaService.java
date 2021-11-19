package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaces.ICita;
import com.citabot.interfaces.IPaciente;
import com.citabot.interfaces.IRegistroClinica;
import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CitaService implements ICitaService {

    @Autowired
    private ICita data;
    @Autowired
    private IPaciente pacienteData;
    @Autowired
    private IRegistroClinica registroClinicaData;


    @Override
    public List<Cita> listar() {
        return (List<Cita>) data.findAll();
    }

    @Override
    public List<Cita> listarById(int id) {
        return (List<Cita>) data.findById(id).get();
    }

    @Override
    public List<Cita> listarByPacienteId(int id) {
        return (List<Cita>) data.findCitaByPaciente(id);
    }

    @Override
    public Cita save(Cita cita) {
        Cita c = new Cita();
        try{
            c = data.save(cita);
        }catch(Error e){
            System.out.printf("Error scheduling appointment: ", e.getMessage());
        }
        return c;
    }

    @Override
    public String delete(int citaId, String estado) {
        Cita c = new Cita();
        String message = "SUCCESS";
        try{
            c = data.findById(citaId).get();
            c.setEstado("CANCELADO");

        }catch(Error error){
            System.out.printf("Error cancelling appointment: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }
}
