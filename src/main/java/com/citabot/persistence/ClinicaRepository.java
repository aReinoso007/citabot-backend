package com.citabot.persistence;

import com.citabot.persistence.crud.ClinicaCRUDRepository;
import com.citabot.persistence.entity.Clinica;

import java.util.List;

public class ClinicaRepository {

    private ClinicaCRUDRepository clinicaCRUDRepository;

    public List<Clinica> getAll(){
        return (List<Clinica>)clinicaCRUDRepository.findAll();
    }

}
