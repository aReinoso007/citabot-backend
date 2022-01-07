package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IHorarioService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.IHorario;
import com.citabot.model.Horario;
import com.citabot.model.RegistroClinica;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
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
    public void deleteHorario(int id) {
        data.deleteById(id);
    }

    @Override
    public List<String> listarDiasDelHorarioPorRegistro(int id) {
        return data.buscarDiasEnRegistro(id);
    }

    @Override
    public List<String> listarFechasDisponibles(int id) {
        /*Aqui le paso un array que tiene el dia,hora inicio, hora fin */
        List<String> dias = data.horarioOrdenadoPorRegistro(id);
        List<String> availableDates;
        try{
            availableDates = obtenerFechasyHorasDisponibles(dias, id);
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

    @Override
    public List<LocalDateTime> listarDiasDisponibles() {

        /*Aqui le paso un array que tiene el dia,hora inicio, hora fin */
        List<String> dias = data.diasOrdenadosPorRegistro();
        List<LocalDateTime> availableDates;
        try{
            availableDates = ObtenerDiasDisponibles(dias);
        }catch (Exception e){
            System.out.printf("excepcion listando dias: "+ e.getMessage());
            return null;
        }
        return availableDates;
    }


    @Override
    public List<Horario> horarioOrdenadoObj(int id) {
        return data.horarioOrdenadoOBJ(id);
    }

    public Timestamp actualizado(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    /*Se obtienen los dias disponibles para agendar las citas */
    public List<LocalDateTime> ObtenerDiasDisponibles(List<String> diasHoras){
        List<LocalDateTime> availableDates = new ArrayList<LocalDateTime>();
        List<String> citasAgendadas = citaData.citasOrdenadasDiasPorRegistro();

        List<LocalDateTime> fechasFiltradas = new ArrayList<>();
        List<Timestamp> filtro = new ArrayList<>();
        List<Timestamp> horarioTimestamp = new ArrayList<>();
        List<Timestamp> agendadasTimestamp = new ArrayList<>();
        List<LocalDateTime> fechasFinales = new ArrayList<>();

        LocalDateTime inicioDate = LocalDateTime.now().withSecond(0).withNano(0);
        LocalDateTime finDate = inicioDate.plusDays(7).withSecond(0).withNano(0);
        for(LocalDateTime id1 = inicioDate; id1.isBefore(finDate); id1 = id1.plusDays(1)) {
            for(int i=0; i< diasHoras.size(); i++) {
                String diaHora = diasHoras.get(i);
                String[] arrOfDia = diaHora.split(",", 4);
                String ldt = id1.getDayOfWeek().toString();
                if(ldt.equals(arrOfDia[0].toUpperCase())) {

                    String horIn = arrOfDia[1].substring(0,2);
                    String minIn = arrOfDia[1].substring(3,5);

                    String horFin = arrOfDia[2].substring(0,2);
                    String minFin = arrOfDia[2].substring(3,5);

                    int horaStart = Integer.parseInt(horIn);
                    int minStart = Integer.parseInt(minIn);
                    int horaEnd = Integer.parseInt(horFin);
                    int minEnd = Integer.parseInt(minFin);
                    LocalDateTime nldt = id1.withHour(horaStart).withMinute(minStart).withSecond(0).withNano(0);
                    LocalDateTime nldtFin = id1.withHour(horaEnd).withMinute(minEnd).withSecond(0).withNano(0);

                    for(LocalDateTime id2 = nldt; id2.isBefore(nldtFin); id2 = id2.plusMinutes(30)) {
                        availableDates.add(id2);
                    }
                }
            }
        }

        horarioTimestamp = localDateTimeATimestamp(availableDates);
        agendadasTimestamp = stringaTimestamp(citasAgendadas);
        filtro = filtrarFechas(horarioTimestamp, agendadasTimestamp);
        fechasFiltradas = timeStampToLocalDateTime(filtro);

        Iterator<LocalDateTime> dateTimeIterator = fechasFiltradas.iterator();
        while(dateTimeIterator.hasNext()){
            LocalDateTime ldt = dateTimeIterator.next();
            if(ldt.isBefore(LocalDateTime.now())){
                dateTimeIterator.remove();
            }
        }
        for(LocalDateTime ldt : fechasFiltradas){
            fechasFinales.add(ldt);
        }

        return fechasFinales;
    }


    /*El id es del registro* y me devuelve las fechas como string*/
    public List<String> obtenerFechasyHorasDisponibles(List<String> diasHoras, int id){
        /*Aqui guardo las fechas generadas, importante para iterar por dias */
        List<LocalDateTime> availableDates = new ArrayList<LocalDateTime>();

        /*Tiene las citas agendadas */
        List<String> citasAgendadas = citaData.citasOrdenadasFechaPorRegistro(id);

        /*Para guardar las fechas que no se repiten */
        List<LocalDateTime> fechasFiltradas = new ArrayList<>();
        List<Timestamp> filtro = new ArrayList<>();
        List<Timestamp> horarioTimestamp = new ArrayList<>();
        List<Timestamp> agendadasTimestamp = new ArrayList<>();
        //List<LocalDateTime> fechasFinales = new ArrayList<>();

        LocalDateTime inicioDate = LocalDateTime.now().withSecond(0).withNano(0);
        LocalDateTime finDate = inicioDate.plusDays(7).withSecond(0).withNano(0);
        for(LocalDateTime id1 = inicioDate; id1.isBefore(finDate); id1 = id1.plusDays(1)) {
            for(int i=0; i< diasHoras.size(); i++) {
                String diaHora = diasHoras.get(i);
                String[] arrOfDia = diaHora.split(",", 4);
                String ldt = id1.getDayOfWeek().toString();
                if(ldt.equals(arrOfDia[0].toUpperCase())) {

                    String horIn = arrOfDia[1].substring(0,2);
                    String minIn = arrOfDia[1].substring(3,5);

                    String horFin = arrOfDia[2].substring(0,2);
                    String minFin = arrOfDia[2].substring(3,5);

                    int horaStart = Integer.parseInt(horIn);
                    int minStart = Integer.parseInt(minIn);
                    int horaEnd = Integer.parseInt(horFin);
                    int minEnd = Integer.parseInt(minFin);
                    LocalDateTime nldt = id1.withHour(horaStart).withMinute(minStart).withSecond(0).withNano(0);
                    LocalDateTime nldtFin = id1.withHour(horaEnd).withMinute(minEnd).withSecond(0).withNano(0);

                    for(LocalDateTime id2 = nldt; id2.isBefore(nldtFin); id2 = id2.plusMinutes(30)) {
                        availableDates.add(id2);
                    }
                }
            }
        }
        /*Pasar de LocalDateTime horario a TimeStamp */
        horarioTimestamp = localDateTimeATimestamp(availableDates);
        agendadasTimestamp = stringaTimestamp(citasAgendadas);
        filtro = filtrarFechas(horarioTimestamp, agendadasTimestamp);
        fechasFiltradas = timeStampToLocalDateTime(filtro);

        /*Obtenemos un iterador */
        List<String> fechasFinalesPasables = new ArrayList<>();
        Iterator<LocalDateTime> dateTimeIterator = fechasFiltradas.iterator();
        while(dateTimeIterator.hasNext()){
            LocalDateTime ldt = dateTimeIterator.next();
            if(ldt.isBefore(LocalDateTime.now())){
                dateTimeIterator.remove();
            }
        }
        for(LocalDateTime ldt : fechasFiltradas){
            //fechasFinales.add(ldt);
            fechasFinalesPasables.add(ldt.toString());
        }
        return fechasFinalesPasables;
    }


    public List<Timestamp> localDateTimeATimestamp(List<LocalDateTime> fechas){
        List<Timestamp> ldt = new ArrayList<>();
        for(int i=0;i<fechas.size(); i++){
            ldt.add(Timestamp.valueOf(fechas.get(i)));
        }
        return ldt;
    }

    public List<LocalDateTime> timeStampToLocalDateTime(List<Timestamp> filtro){
        List<LocalDateTime> filtradas = new ArrayList<>();
        for(int i=0; i<filtro.size();i++){
            Timestamp ts = filtro.get(i);
            filtradas.add(ts.toLocalDateTime());
        }
        return filtradas;
    }

    public List<Timestamp> stringaTimestamp(List<String> agendadas){
        List<Timestamp> convertidas = new ArrayList<>();
        for(int i=0; i <agendadas.size();i++){
            convertidas.add(Timestamp.valueOf(agendadas.get(i)));
        }
        return convertidas;
    }

    public List<Timestamp> filtrarFechas(List<Timestamp> fechas, List<Timestamp> agendadas){
        List<Timestamp> filtro = new ArrayList<>(fechas);
        filtro.removeAll(agendadas);
        return filtro;
    }

}
