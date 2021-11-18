package com.citabot.persistence;

import com.citabot.persistence.crud.CitaCRUDRepository;
import com.citabot.persistence.entity.Cita;

import java.util.List;

public class CitaRepository {

    private CitaCRUDRepository citaCRUDRepository;

    public List<Cita> getAll(){
        return (List<Cita>) citaCRUDRepository.findAll();
    }

}
