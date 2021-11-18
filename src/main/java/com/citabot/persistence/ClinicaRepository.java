package com.citabot.persistence;

import com.citabot.persistence.crud.ClinicaCRUDRepository;
import com.citabot.persistence.entity.Clinica;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClinicaRepository {

    private ClinicaCRUDRepository clinicaCRUDRepository;

    public List<Clinica> getAll(){
        return (List<Clinica>)clinicaCRUDRepository.findAll();
    }

}
