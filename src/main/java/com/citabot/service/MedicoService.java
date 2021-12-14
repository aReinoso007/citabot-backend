package com.citabot.service;

import com.citabot.exceptions.EtAuthException;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaces.IMedico;
import com.citabot.model.Medico;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
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

    /*@Autowired
    PasswordEncoder encoder;*/

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
    public Medico save(Medico medico) {
        String email = null;
        String hashedPwd = BCrypt.hashpw(medico.getPassword(), BCrypt.gensalt(10));
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(medico.getEmail() != null)  email = medico.getEmail().toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");
        medico.setEmail(email);
        medico.setPassword(hashedPwd);
        medico.setCreatedAt(actualizado());
        medico.setRole("ROLE_USER");
        medico.setUpdatedAt(actualizado());
        medico.setEstado("activado");
        return data.save(medico);
    }

    /*Slogan, descripcion y numero contacto */
    @Override
    public Medico update(long id, Medico medico) {
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
    public String delete(long id) {
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
    public Medico buscarPorId(long id) {
        return (Medico) data.findMedicoByUsuarioId(id);
    }

    @Override
    public List<Medico> Listar_medicos_especialidad(int idEspecialidad) {
        return data.listarPorEspecialidadId(idEspecialidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Medico findByEmail(String email) {
        Optional<Medico> medico = data.findMedicoByEmail(email);
        return medico.isEmpty() ? null: medico.get();
    }

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


}
