package com.citabot.service;

import com.citabot.exceptions.EtAuthException;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaces.IMedico;
import com.citabot.model.Medico;
import com.citabot.model.formulario.FMedico;
import com.citabot.model.formulario.FUpdateMedico;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class MedicoService implements IMedicoService {



    @Autowired
    private IMedico data;

    @Override
    @Transactional(readOnly = true)
    public List<Medico> listar() {
        return (List<Medico>) data.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> listarByName(String name) {
        return (List<Medico>) data.findMedicoByNombre(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Medico> findById(long id) {
        return data.findById(id);
    }

    @Override
    public Medico validateMedico(String email, String password) throws EtAuthException {
        if( email!=null) email = email.toLowerCase();
        return data.findMedicoByEmailAndPassword(email, password);
    }

    @Override
    public Medico save(FMedico form) {
        Medico medico = formularioAMedico(form);
        String hashedPwd = BCrypt.hashpw(medico.getPassword(), BCrypt.gensalt(10));
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(findByEmail(medico.getEmail())!=null) throw new EtAuthException("Email ya registrado");
        if(!pattern.matcher(medico.getEmail()).matches())
            throw new EtAuthException("Invalid email format");
        medico.setPassword(hashedPwd);
        medico.setCreatedAt(actualizado());
        medico.setRole("ROLE_USER");
        medico.setUpdatedAt(actualizado());
        medico.setEstado("ACTIVO");
        return data.save(medico);
    }

    /*Slogan, descripcion y numero contacto */
    @Override
    public Medico update(long id, FUpdateMedico form) {
        Medico medicoDB = null;

        if(!data.existsById(id)){
            return null;
        }else{
            medicoDB = data.findById(id).get();
            medicoDB.setSlogan(form.getSlogan());
            medicoDB.setDescripcion(form.getDescripcion());
            medicoDB.setNumeroContacto(form.getNumeroContacto());
            medicoDB.setUpdatedAt(actualizado());
            return data.save(medicoDB);
        }

    }

    @Override
    public String delete(long id) {
        String message = "SUCCESS";
        Medico medico = findById(id).get();
        if(medico!=null){
            try{
                medico.setEstado("DESACTIVADO");
                data.save(medico);
                return message;
            }catch (Exception exception){
                System.out.printf("Error deleting: ", exception.getMessage());
                message = "FAILED";
            }
        }
        return message;
    }


    @Override
    @Transactional(readOnly = true)
    public Medico buscarPorId(long id) {
        return  data.findMedicoByUsuarioId(id);
    }

    @Override
    public List<Medico> Listar_medicos_especialidad(int idEspecialidad) {
        return data.listarPorEspecialidadId(idEspecialidad);
    }

    @Override
    public List<Medico> listar_medicos_especialidadDia(int idEspecialidad, String dia) {
        return data.listarPorDiaEspecilaidad(idEspecialidad, dia);
    }

    @Override
    public List<Medico> Listar_medicos_clinica(int idClinica) {
        return data.listarPorClinica(idClinica);
    }

    @Override
    @Transactional(readOnly = true)
    public Medico findByEmail(String email) {
        Optional<Medico> medico = data.findMedicoByEmail(email);
        return medico.isEmpty() ? null: medico.get();
    }
    /*Esto es para el login */
    @Override
    public Medico findByEmailAndContrasena(String email, String password) {
        try{
            Medico medico = data.findMedicoByEmail(email).get();
            if(!BCrypt.checkpw(password, medico.getPassword()))
                throw new EtAuthException("Email/password invalido");
            return medico;
        }catch (EmptyResultDataAccessException e){
            throw new EtAuthException("Credenciales incorrectas");
        }
    }

    @Override
    public Medico findByUsername(String username) {
        return data.findMedicoByUsername(username);
    }

    @Override
    public boolean existeUsername(String username) {
        return data.existsByUsername(username);
    }

    @Override
    public Medico loginUsernamePassword(String username, String password) {
        return data.findMedicoByUsernameAndPassword(username, password);
    }

    /*Para poner la fecha y ahora de actualizacion */
    public Timestamp actualizado(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }
    /*Transformar el formulario de registro de medico a medico
    * porque no puedo enviar por el json la contrasena, cuando se hacen
    * consultas se envia el obj medico menos password*/
    private Medico formularioAMedico(FMedico form){
        Medico medico = new Medico();
        medico.setNombre(form.getNombre());
        medico.setApellido(form.getApellido());
        medico.setUsername(form.getUsername());
        medico.setEstado(form.getEstado());
        medico.setEmail(form.getEmail());
        medico.setRecoveryEmail(form.getRecoveryEmail());
        medico.setPassword(form.getPassword());
        medico.setNumeroContacto(form.getNumeroContacto());
        medico.setSlogan(form.getSlogan());
        medico.setProfesion(form.getProfesion());
        medico.setDescripcion(form.getDescripcion());
        medico.setRole(form.getRole());
        return medico;
    }


}
