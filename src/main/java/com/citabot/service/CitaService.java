package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.ICita;
import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private ICita data;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IRegistroClinicaService registroService;


    @Override
    public List<Cita> listar() {
        return (List<Cita>) data.findAll();
    }

    @Override
    public Optional<Cita> listarById(int id) {
        return data.findById(id);
    }

    @Override
    public List<Cita> listarByPacienteId(int id) {
        return (List<Cita>) data.getCitasByPacienteId(id);
    }

    @Override
    public Cita save(Cita cita) {
        Cita citaDb = new Cita();
        try{
            citaDb = data.save(cita);
            return citaDb;
        }catch (Error error){
            System.out.printf("error saving cita: ", error.getMessage());
        }
        return citaDb;
    }

    @Override
    public String delete(int citaId) {
        Cita c = new Cita();
        String message = "SUCCESS";
        try{
            if(data.existsById(citaId)){
                c = data.findById(citaId).get();
                c.setEstado("CANCELADO");
                c.setUpdateAt(setTime());
                data.save(c);
            }else{
                return "Cita no existe";
            }

        }catch(Error error){
            System.out.printf("Error cancelling appointment: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }

    @Override
    public Cita update(int citaId, String estado) {
        Cita c = new Cita();
        try {
            if(data.existsById(citaId)){
                c = data.findById(citaId).get();
                c.setEstado(estado);
                c.setUpdateAt(setTime());
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
        return data.findByPacienteAndEstado(pId, estado);
    }

    public Timestamp setTime(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    /*Para formatear horaInicio y horaFin */
    public Time getFormattedTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
        long ms = sdf.parse(time).getTime();
        Time t =new Time(ms);
        return t;
    }
    /*
    @Override
    public Cita save(int paId, int regId, Cita cita, String hInicio, String hFin) {
        Cita c = new Cita();
        Time horaInicio= null;
        Time horaFin = null;
        Paciente p = new Paciente();
        RegistroClinica registroClinica = new RegistroClinica();

        try{
            p = pacienteService.findById(paId);
            System.out.printf("Paciente: ", p.getNombre());
            registroClinica = registroService.findById(regId).get();
            System.out.printf("Registro: ", registroService.findById(regId).get());
            horaInicio = getFormattedTime(hInicio);
            horaFin = getFormattedTime(hFin);
            cita.setHoraInicio(horaInicio);
            //cita.setPaciente(p);
            cita.setClinicaMedico(registroClinica);
            cita.setHoraFin(horaFin);
            cita.setEstado("ACTIVO");
            cita.setCreatedAt(setTime());
            cita.setUpdateAt(setTime());
            c = data.save(cita);
            return c;
        }catch(Error | ParseException e){
            System.out.printf("Error scheduling appointment: ", e.getMessage());
        }
        return c;
    }
     */
}
