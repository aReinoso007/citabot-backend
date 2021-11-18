package com.citabot.persistence;

import com.citabot.persistence.crud.MedicoCRUDRepository;
import com.citabot.persistence.entity.Medico;

import java.util.List;

public class MedicoRepository {

    private MedicoCRUDRepository medicoCRUDRepository;

    public List<Medico> getAll(){
        return (List<Medico>) medicoCRUDRepository.findAll();
    }

}
