package com.citabot.persistence;

import com.citabot.persistence.crud.PacienteCRUDRepository;
import com.citabot.persistence.entity.Paciente;

import java.util.List;

public class PacienteRepository {
    private PacienteCRUDRepository pacienteCRUDRepository;


    public List<Paciente> getAll(){
        return (List<Paciente>) pacienteCRUDRepository.findAll();
    }

}
