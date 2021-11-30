package com.citabot.service;

import com.citabot.interfaceService.ICitaService;
import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.IPaciente;
import com.citabot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Paciente findById(int id) {
        return data.findPacienteByUsuarioId(id).orElse(null);
    }

    @Override
    public Paciente save(Paciente paciente) {
        paciente.setCreatedAt(actualizado());
        paciente.setUpdatedAt(actualizado());
        paciente.setEstado("activado");
        return data.save(paciente);
    }

    @Override
    public Paciente edit(Paciente paciente) {
        paciente.setEstado("ACTIVADO");
        return data.save(paciente);
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try{
            data.deleteById(id);
        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
            message = "FAILED";
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
        System.out.printf("Retorno paciente por email: ", paciente);
        return paciente.isEmpty() ? null: paciente.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente buscarPorEmailYContrasena(String email, String password) {
        return data.findPacienteByEmailAndPassword(email, password);
    }
    /*Actualizar: nombre, apellido, emailRecovery, numeroContacto, tipoSangre */
    @Override
    public Paciente update(int id, Paciente paciente) {
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

    /*Para poner la fecha y ahora de actualizacion */
    public Timestamp actualizado(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

}
