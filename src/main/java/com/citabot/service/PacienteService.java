package com.citabot.service;

import com.citabot.interfaceService.IPacienteService;
import com.citabot.interfaces.IPaciente;
import com.citabot.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PacienteService implements IPacienteService {

    @Autowired
    private IPaciente data;

    @Override
    public List<Paciente> listar() {
        return (List<Paciente>) data.findAll();
    }

    @Override
    public List<Paciente> listarByNombre(String n) {
        return (List<Paciente>) data.findPacientesByNombre(n);
    }

    @Override
    public Paciente findById(int id) {
        return data.findPacienteById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return data.save(paciente);
    }

    @Override
    public Paciente edit(Paciente paciente) {
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
    public Optional<Paciente> listarByNombreYApellido(String n) {
        String[] nombre = n.split(" ");
        return (Optional<Paciente>) data.findPacientesByNombreOrApellido(nombre[0].toString(), nombre[1].toString());
    }
}
