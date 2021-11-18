package com.citabot.persistence;

import com.citabot.persistence.crud.CitaCRUDRepository;
import com.citabot.persistence.entity.Cita;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CirugiaRepository {

    private CitaCRUDRepository citaCRUDRepository;

    public List<Cita> findAll(){
        return (List<Cita>) citaCRUDRepository.findAll();
    }

}
