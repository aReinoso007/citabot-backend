package com.citabot.persistence;

import com.citabot.persistence.crud.HorarioCRUDRepository;
import com.citabot.persistence.entity.Horario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HorarioRepository {

    private HorarioCRUDRepository horarioCRUDRepository;

    public List<Horario> findAll(){
        return (List<Horario>) horarioCRUDRepository.findAll();
    }

}
