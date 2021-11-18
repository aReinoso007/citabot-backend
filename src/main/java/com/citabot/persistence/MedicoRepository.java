package com.citabot.persistence;

import com.citabot.persistence.crud.MedicoCRUDRepository;
import com.citabot.persistence.entity.Medico;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepository {

    private MedicoCRUDRepository medicoCRUDRepository;

    public List<Medico> getAll(){
        return (List<Medico>) medicoCRUDRepository.findAll();
    }

}
