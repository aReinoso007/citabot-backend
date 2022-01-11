package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IHorarioService;
import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.ICita;
import com.citabot.interfaces.MailService;
import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;
import com.citabot.model.clases.Mail;
import com.citabot.model.formulario.interfaces.CitaConstl;
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

    @Autowired
    MailService serviceMail;

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
        Mail mail = new Mail();
        Paciente pacienteDB = new Paciente();
        RegistroClinica registroClinicaDB = new RegistroClinica();
        System.out.printf("fecha obtenida: " + formularioCita.getFechaCita());
        Timestamp fecha = localDateTimeToTimeStamp(formularioCita.getFechaCita());
        System.out.printf("fecha transformada: " + fecha);
        try {
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

            /*Se crean correos electronicos para notificar al paciente y al doctor sobre la nueva cita agendada*/
            String content="Usted ha agendado una cita médica con los siguientes datos:"+"\n" +
                    "\n" +" Doctor/a: "+registroClinicaDB.getMedico().getNombre()+" "+registroClinicaDB.getMedico().getApellido() +"\n"+ " Clínica: "+
                    registroClinicaDB.getClinica().getNombreClinica()+"\n" +"Fecha: "+cita.getFechaCita().toString();
            mail.setMailContent(content);
            enviarCorreo("informarAgendamiento","paciente",pacienteDB.getEmail(),mail);
            mail=new Mail();
            content="Se ha agendado una cita médica con los siguientes datos:"+"\n" +
                    "\n" +" Paciente: "+pacienteDB.getNombre()+" "+pacienteDB.getApellido() +"\n"+ " Clínica: "+
                    registroClinicaDB.getClinica().getNombreClinica()+"\n" +"Fecha: "+cita.getFechaCita().toString();
            mail.setMailContent(content);
            enviarCorreo("informarAgendamiento","doctor",registroClinicaDB.getMedico().getEmail(),mail);

            return citaDb;
        } catch (Error error) {
            System.out.printf("error saving cita: ", error.getMessage());
        }
        return citaDb;
    }

    /*Este metodo crea y envia un correo tomando en cuenta el tipo de correo (cancelar cita, informar agendamiento)
    * el usuario hace referencia al paciente o medico y finalmente la direccion de correo de destino */
    public void enviarCorreo(String tipoCorreo, String usuario, String correo, Mail mail){
        //Mail mail = new Mail();
        mail.setMailFrom("citadoc@gmail.com");
        if(tipoCorreo.equals("informarAgendamiento")&&usuario.equals("paciente")){
            mail.setMailTo(correo);
            mail.setMailSubject("Agendamiento de cita médica");
        }else if(tipoCorreo.equals("informarAgendamiento")&&usuario.equals("doctor")){
            mail.setMailTo(correo);
            mail.setMailSubject("Agendamiento de cita médica");
        }else if(tipoCorreo.equals("cancelar")&&usuario.equals("paciente")){
            mail.setMailTo(correo);
            mail.setMailSubject("Cancelación de cita médica");
        }else if(tipoCorreo.equals("cancelar")&&usuario.equals("doctor")){
            mail.setMailTo(correo);
            mail.setMailSubject("Cancelación de cita médica");
        }
        serviceMail.sendEmail(mail);
    }

    @Override
    public String delete(int citaId) {
        Cita c = new Cita();
        String message = "SUCCESS";
        try {
            if (data.existsById(citaId)) {
                c = data.findById(citaId).get();
                c.setEstado("CANCELADO");
                c.setUpdateAt(actualizado());
                data.save(c);
            } else {
                return "Cita no existe";
            }

        } catch (Error error) {
            System.out.printf("Error cancelling appointment: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }

    @Override
    public Cita update(int citaId, String estado) {
        Cita c = new Cita();
        Mail mail = new Mail();
        Paciente pacienteDB = new Paciente();
        RegistroClinica registroClinicaDB = new RegistroClinica();
        try {
            if (data.existsById(citaId)) {
                c = data.findById(citaId).get();
                c.setCreatedAt(actualizado());
                c.setUpdateAt(actualizado());
                c.setEstado(estado);
                data.save(c);

                pacienteDB = pacienteData.findById(c.getPaciente().getUsuarioId());
                registroClinicaDB = registroData.findById(c.getClinicaMedico().getRegistroClinicaId()).get();
                /*Se crean correos electronicos para notificar al paciente y al doctor sobre la nueva cita agendada*/
                String content="Usted ha cancelado la cita médica con los siguientes datos:"+"\n" +
                        "\n" +" Doctor/a: "+registroClinicaDB.getMedico().getNombre()+" "+registroClinicaDB.getMedico().getApellido() +"\n"+ " Clínica: "+
                        registroClinicaDB.getClinica().getNombreClinica()+"\n" +"Fecha: "+c.getFechaCita().toString();
                mail.setMailContent(content);
                enviarCorreo("cancelar","paciente",pacienteDB.getEmail(),mail);
                mail=new Mail();
                content="Se ha cancelado la cita médica con los siguientes datos:"+"\n" +
                        "\n" +" Paciente: "+pacienteDB.getNombre()+" "+pacienteDB.getApellido() +"\n"+ " Clínica: "+
                        registroClinicaDB.getClinica().getNombreClinica()+"\n" +"Fecha: "+c.getFechaCita().toString();
                mail.setMailContent(content);
                enviarCorreo("cancelar","doctor",registroClinicaDB.getMedico().getEmail(),mail);

                return c;
            } else {
                return null;
            }
        } catch (Error e) {
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
        return data.getFechasCitaPorRegistro(id, fechaActual());
    }

    @Override
    public List<String> citasOrdenadasFechaPorMedico(int id) {
        return data.getDiasCitaPorMedico(fechaActual(),id);
    }

    @Override
    public List<String> citasOrdenadasFechaPorClinica(int idClinica, int idMedico) {
        return data.getDiasCitaPorClinica(fechaActual(),idClinica, idMedico);
    }

    @Override
    public List<String> citasOrdenadasDiasPorRegistro() {
        return data.getDiasCitaPorRegistro(fechaActual());
    }

    @Override
    public List<Cita> getHistorial(long id) {
        return data.getAllCitasByMedicoId(id);
    }

    @Override
    public List<Cita> getTodayCitas(long id) {
        return data.getTodayCitas(id);
    }
    public List<CitaConstl> Listar_citas_paciente(int idPaciente) {
        System.out.printf("Data: " + data.listarCitaPorPacienteId(idPaciente));
        return data.listarCitaPorPacienteId(idPaciente);

    }

    @Override
    public List<CitaConstl> obtenerCitaDetalle(int idCita) {
        return data.getCitaById(idCita);
    }

    public Timestamp actualizado() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    /* Para formatear horaInicio y horaFin */
    public Time getFormattedTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
        long ms = sdf.parse(time).getTime();
        Time t = new Time(ms);
        return t;
    }

    /* Para transformar la fecha enviada en el registro de cita */
    public Timestamp localDateTimeToTimeStamp(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt);
    }

    public Timestamp fechaActual(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

}
