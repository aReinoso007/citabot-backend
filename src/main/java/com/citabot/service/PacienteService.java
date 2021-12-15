package com.citabot.service;

import com.citabot.exceptions.EtAuthException;
import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.IPaciente;
import com.citabot.model.*;
import com.citabot.model.formulario.FPaciente;
import lombok.SneakyThrows;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPaciente data;
    @Autowired
    private IRegistroClinicaService registroData;
    @Autowired
    private ICitaService citaData;
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
    public Paciente findById(long id) {
        return data.findPacienteByUsuarioId(id).orElse(null);
    }

    @SneakyThrows
    @Override
    public Paciente save(FPaciente form) {
        Paciente paciente = formularioAPaciente(form);
        String hashedPwd = BCrypt.hashpw(paciente.getPassword(), BCrypt.gensalt(10));
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(findByEmail(paciente.getEmail())!=null) throw new EtAuthException("Email ya registrado");
        if(!pattern.matcher(paciente.getEmail()).matches())
            throw new EtAuthException("Formato de email invalido");
        paciente.setPassword(hashedPwd);
        paciente.setCreatedAt(actualizado());
        paciente.setUpdatedAt(actualizado());
        paciente.setRole("ROLE_USER");
        paciente.setEstado("ACTIVO");
        return data.save(paciente);
    }

    @Override
    public Paciente edit(Paciente paciente) {
        paciente.setEstado("ACTIVADO");
        return data.save(paciente);
    }

    @Override
    public String delete(long id) {
        String message = "SUCCESS";
        Paciente paciente = findById(id);
        if(paciente!=null){
            try{
                paciente.setEstado("DESACTIVADO");
                data.save(paciente);
                return message;
            }catch (Error error){
                System.out.printf("Error deleting: ", error.getMessage());
                message = "FAILED";
            }
        }

        return message;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> listarByNombreYApellido(String n) {
        String[] nombre = n.split(" ");
        return data.findPacientesByNombreOrApellido(nombre[0].toString(), nombre[1].toString());
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente findByEmail(String email) {
        Optional<Paciente> paciente = data.findPacienteByEmail(email);
        return paciente.isEmpty() ? null: paciente.get();
    }
    /*Esto es para el login */
    @Override
    @Transactional(readOnly = true)
    public Paciente buscarPorEmailYContrasena(String email, String password) {
        try{
            Paciente paciente = data.findPacienteByEmail(email).get();
            if(!BCrypt.checkpw(password, paciente.getPassword()))
                throw new EtAuthException("Email/password invalidos");
            return paciente;
        }catch (EmptyResultDataAccessException e){
            throw  new EtAuthException("Credenciales incorrectas");
        }
    }
    /*Actualizar: nombre, apellido, emailRecovery, numeroContacto, tipoSangre */
    @Override
    public Paciente update(long id, Paciente paciente) {
        Paciente pacienteDb = null;
        pacienteDb = data.findById(id).get();
        if(!data.existsById(id)){
            return null;

        }else{
            pacienteDb.setNombre(paciente.getNombre());
            pacienteDb.setApellido(paciente.getApellido());
            pacienteDb.setRecoveryEmail(paciente.getRecoveryEmail());
            pacienteDb.setUpdatedAt(actualizado());
            pacienteDb.setTipoSangre(paciente.getTipoSangre());
            pacienteDb.setNumeroContacto(paciente.getNumeroContacto());
            pacienteDb.setFechaNacimiento(paciente.getFechaNacimiento());
            return data.save(pacienteDb);
        }
    }

    @Override
    public Paciente buscarPorUsername(String username) {
        return data.findPacienteByUsername(username);
    }

    @Override
    public Boolean existeUsername(String username) {
        return data.existsByUsername(username);
    }


    /*Para poner la fecha y ahora de actualizacion */
    public Timestamp actualizado(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    public Paciente formularioAPaciente(FPaciente form) throws ParseException {
        Paciente paciente = new Paciente();
        paciente.setNombre(form.getNombre());
        paciente.setApellido(form.getApellido());
        paciente.setUsername(form.getUsername());
        paciente.setEstado(form.getEstado());
        paciente.setEmail(form.getEmail());
        paciente.setRecoveryEmail(form.getRecoveryEmail());
        paciente.setPassword(form.getPassword());
        paciente.setNumeroContacto(form.getNumeroContacto());
        paciente.setTipoSangre(form.getTipoSangre());
        paciente.setPhotoUrl(form.getPhotoUrl());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(form.getFechaNacimiento());
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        paciente.setFechaNacimiento(sqlDate);
        paciente.setGenero(form.getGenero());
        paciente.setRole(form.getRole());
        return paciente;
    }

}
