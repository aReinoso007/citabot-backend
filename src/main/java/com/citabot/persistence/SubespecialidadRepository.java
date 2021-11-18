package com.citabot.persistence;

import com.citabot.persistence.crud.SubespecialidadCRUDRepository;
import com.citabot.persistence.entity.Subespecialidad;

import java.util.List;

public class SubespecialidadRepository {

    private SubespecialidadCRUDRepository subespecialidadCRUDRepository;

    public List<Subespecialidad> getAll(){
        return (List<Subespecialidad>) subespecialidadCRUDRepository.findAll();
    }

}
