package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IHorarioService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.IHorario;
import com.citabot.model.Horario;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService implements IHorarioService {

    @Autowired
    private IHorario data;
    @Autowired
    private IRegistroClinicaService registroData;
    @Autowired
    private ICitaService citaData;

    @Override
    public List<Horario> listar() {
        return (List<Horario>) data.findAll();
    }

    @Override
    public List<Horario> listarByClinicaRegistro(int id) {
        return data.buscarPorRegistro(id);
    }

    @Override
    public Optional<Horario> listarById(int id) {
        return data.findById(id);
    }

    @Override
    public Horario save(int idRegistro, Horario horario) {
        Horario h = new Horario();
        RegistroClinica registroClinica = new RegistroClinica();
        RegistroClinica rc = new RegistroClinica();
        try{
            registroClinica = registroData.findById(idRegistro).get();
            horario.setRegistroClinica(registroClinica);
            h = data.save(horario);
            return h;
        }catch (Error e){
            System.out.printf("Error saving: ", e.getMessage());
        }
        return h;
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try{
            data.deleteById(id);
        }catch (Error e){
            System.out.printf("Error deleting: ", e.getMessage());
            message = "FAILED";
        }
        return  message;
    }

    @Override
    public List<String> listarDiasDelHorarioPorRegistro(int id) {
        return data.buscarDiasEnRegistro(id);
    }

    @Override
    public List<LocalDateTime> listarFechasDisponibles(int id) {
        /*Aqui le paso un array que tiene el dia,hora inicio, hora fin */
        List<String> dias = data.horarioOrdenadoPorRegistro(id);
        List<LocalDateTime> availableDates;
        try{
            availableDates = obtenerFechasyHorasDisponibles(dias);
        }catch (Exception e){
            System.out.printf("excepcion listando fechas: "+ e.getMessage());
            return null;
        }
        return availableDates;
    }

    @Override
    public List<String> horariosRegistroOrdenado(int id) {
        return data.horarioOrdenadoPorRegistro(id);
    }

    public Timestamp actualizado(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    public List<LocalDateTime> obtenerFechasyHorasDisponibles(List<String> diasHoras){
        List<LocalDateTime> availableDates = new ArrayList<LocalDateTime>();

        LocalDateTime inicioDate = LocalDateTime.now();
        LocalDateTime finDate = inicioDate.plusDays(7);
        for(LocalDateTime id1 = inicioDate; id1.isBefore(finDate); id1 = id1.plusDays(1)) {
            for(int i=0; i< diasHoras.size(); i++) {
                String diaHora = diasHoras.get(i);
                String[] arrOfDia = diaHora.split(",", 4);
                String ldt = id1.getDayOfWeek().toString();
                if(ldt.equals(arrOfDia[0].toUpperCase())) {

                    String horIn = arrOfDia[1].substring(0,2);
                    String minIn = arrOfDia[1].substring(6,8);
                    String horFin = arrOfDia[2].substring(0,2);
                    String minFin = arrOfDia[2].substring(3,5);
                    int horaStart = Integer.parseInt(horIn);
                    int minStart = Integer.parseInt(minIn);
                    int horaEnd = Integer.parseInt(horFin);
                    int minEnd = Integer.parseInt(minFin);
                    LocalDateTime nldt = id1.withHour(horaStart).withMinute(minStart).withNano(01);
                    LocalDateTime nldtFin = id1.withHour(horaEnd).withMinute(minEnd).withNano(007);
                    for(LocalDateTime id2 = nldt; id2.isBefore(nldtFin); id2 = id2.plusMinutes(30)) {
                        availableDates.add(id2);
                    }
                }
            }
        }
        return availableDates;
    }
}
