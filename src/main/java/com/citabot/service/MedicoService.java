package com.citabot.service;

import com.citabot.exceptions.EtAuthException;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaces.IMedico;
import com.citabot.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Medico> findById(int id) {
        return data.findById(id);
    }

    @Override
    public Medico validateMedico(String email, String password) throws EtAuthException {
        if( email!=null) email = email.toLowerCase();
        return data.findMedicoByEmailAndPassword(email, password);
    }

    @Override
    public Medico save(Medico medico) {
        /*Add encryption */
        String email = null;
        String encryptedPassword = null;
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(medico.getEmail() != null)  email = medico.getEmail().toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");
        medico.setPassword(encryptedPassword);
        System.out.printf("encryptedPassword: ", encryptedPassword);
        medico.setEmail(email);
        medico.setCreatedAt(actualizado());
        medico.setUpdatedAt(actualizado());
        medico.setEstado("activado");
        return data.save(medico);
    }

    /*Slogan, descripcion y numero contacto */
    @Override
    public Medico update(int id, Medico medico) {
        Medico medicoDB = null;

        if(!data.existsById(id)){
            return null;
        }else{
            medicoDB = data.findById(id).get();
            medicoDB.setSlogan(medico.getSlogan());
            medicoDB.setDescripcion(medico.getDescripcion());
            medicoDB.setNumeroContacto(medico.getNumeroContacto());
            medicoDB.setUpdatedAt(actualizado());
            return data.save(medicoDB);
        }

    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try{
            data.deleteById(id);
        }catch (Exception exception){
            System.out.printf("Error deleting: ", exception.getMessage());
            message = "FAILED";
        }
        return message;
    }


    @Override
    @Transactional(readOnly = true)
    public Medico buscarPorId(int id) {
        return (Medico) data.findMedicoByUsuarioId(id);
    }

    @Override
    public List<Medico> Listar_medicos_especialidad(int idEspecialidad) {
        return (List<Medico>) data.listarPorEspecialidadId(idEspecialidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Medico findByEmail(String email) {
        Optional<Medico> medico = data.findMedicoByEmail(email);
        return medico.isEmpty() ? null: medico.get();
    }

    @Override
    public Medico findByEmailAndContrasena(String email, String password) {

        return data.findMedicoByEmailAndPassword(email, password);
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


}
