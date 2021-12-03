package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IHorarioService;
import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.ICita;
import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;
import com.citabot.model.formulario.FCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private ICita data;
    @Autowired
    private IPacienteService pacienteData;
    @Autowired
    private IRegistroClinicaService registroData;
    @Autowired
    private IHorarioService horarioService;

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
    public Cita save(FCita formularioCita, int paId, int regId) {
        Cita citaDb = new Cita();
        Cita cita = new Cita();
        Paciente pacienteDB = new Paciente();
        RegistroClinica registroClinicaDB = new RegistroClinica();
        System.out.printf("fecha obtenida: "+formularioCita.getFechaCita());
        Timestamp fecha = localDateTimeToTimeStamp(formularioCita.getFechaCita());
        System.out.printf("fecha transformada: "+fecha);
        try{
            pacienteDB = pacienteData.findById(paId);
            registroClinicaDB = registroData.findById(regId).get();
            cita.setSintomas(formularioCita.getSintomas());
            cita.setFechaCita(fecha);
            cita.setEstado("pendiente");
            cita.setCreatedAt(actualizado());
            cita.setUpdateAt(actualizado());
            cita.setPaciente(pacienteDB);
            cita.setClinicaMedico(registroClinicaDB);
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
                c.setUpdateAt(actualizado());
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
                c.setCreatedAt(actualizado());
                c.setUpdateAt(actualizado());
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
        return data.findByPacienteAndEstado(pId, estado);
    }

    @Override
    public List<Cita> listarByRegistroId(int id) {
        return (List<Cita>) data.getCitasByRegistroId(id);
    }

    /* */
    @Override
    public List<String> citasOrdenadasFechaPorRegistro(int id) {
        return data.getFechasCitaPorRegistro(id);
    }

    public Timestamp actualizado(){
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
    /*Para transformar la fecha enviada en el registro de cita */
    public Timestamp localDateTimeToTimeStamp(LocalDateTime ldt){
        return Timestamp.valueOf(ldt);
    }


}
