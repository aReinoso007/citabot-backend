package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaces.ICita;
import com.citabot.interfaces.IPaciente;
import com.citabot.interfaces.IRegistroClinica;
import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private ICita data;
    @Autowired
    private IPaciente pacienteData;



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
    public String delete(int citaId) {
        Cita c;
        String message = "SUCCESS";
        try{
            c = data.findById(citaId).get();
            c.setEstado("CANCELADO");
            data.save(c);
        }catch(Error error){
            System.out.printf("Error cancelling appointment: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }

    @Override
    public Cita update(int citaId, String estado) {
        Cita c;
        try {
            if(data.existsById(citaId)){
                c = data.findById(citaId).get();
                c.setEstado(estado);
                data.save(c);
                return  c;
            }else{
                return null;
            }
        }catch (Error e){
            System.out.printf("Error updating: ", e.getMessage());
        }
        return c;
    }

    @Override
    public Optional<Cita> getCitasByPacienteIdAndEstado(int pId, String estado) {
        return Optional.empty();
    }
}
