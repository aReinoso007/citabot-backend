package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IHorarioService;
import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.ICita;
import com.citabot.model.Cita;
import com.citabot.model.Horario;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.printf("horario: ", semanasenCalendario(YearMonth.now()));
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
    public Cita save(Cita cita, int paId, int regId) {
        Cita citaDb = new Cita();
        Paciente pacienteDB = new Paciente();
        RegistroClinica registroClinicaDB = new RegistroClinica();

        try{
            pacienteDB = pacienteData.findById(paId);
            registroClinicaDB = registroData.findById(regId).get();
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

    public List<LocalDate> semanasenCalendario(YearMonth mes){
        List<LocalDate> diasHorario = new ArrayList<>();
        return diasHorario;
    }

    public List<LocalDate> dayInCalendar(YearMonth month){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);
        List<LocalDate> fechasDisponibles = new ArrayList<>();

        List<Horario> diasEnRegistro = null;
        /*cambiar la asignacion del id a dinamico */
        diasEnRegistro = horarioService.listarDiasDelHorarioPorRegistro(2);

        List<String> diasHorario = new ArrayList<>(); diasHorario = dias(diasEnRegistro);
        List<LocalDate> datesAvailable = new ArrayList<>();




        return fechasDisponibles;
    }

    public List<String> dias(List<Horario> dias){
        List<String> days = new ArrayList<>();
        for(int i=0; i < dias.size(); i++){
            days.add(dias.get(i).getDia());
        }
        /*Transformar todos los dias a upperCase */
        dias.stream().map(ds -> ds.getDia().toUpperCase());
        return days;
    }

    public List<LocalDate> obtenerFechasDisponibles(List<String> dias){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);
        List<LocalDate> availableDates = new ArrayList<LocalDate>();

        for(LocalDate d1 = startDate; d1.isBefore(endDate); d1 = d1.plusDays(1)){

            for(int j =0; j < dias.size(); j++){
                String ddias = "";
                ddias = dias.get(j).toString();
                String dow = d1.getDayOfWeek().toString();
                if(dow == (ddias)) {
                    availableDates.add(d1);
                }
                dow ="";
                ddias = "";
            }
        }

        System.out.println("fechas disponibles: "+availableDates);
        return availableDates;
    }

}
