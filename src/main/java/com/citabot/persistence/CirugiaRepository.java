package com.citabot.persistence;

import com.citabot.persistence.crud.CitaCRUDRepository;
import com.citabot.persistence.entity.Cita;

import java.util.List;

public class CirugiaRepository {

    private CitaCRUDRepository citaCRUDRepository;

    public List<Cita> findAll(){
        return (List<Cita>) citaCRUDRepository.findAll();
    }

}
