package com.citabot.persistence;

import com.citabot.persistence.crud.SubespecialidadCRUDRepository;
import com.citabot.persistence.entity.Subespecialidad;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubespecialidadRepository {

    private SubespecialidadCRUDRepository subespecialidadCRUDRepository;

    public List<Subespecialidad> getAll(){
        return (List<Subespecialidad>) subespecialidadCRUDRepository.findAll();
    }

    public List<Subespecialidad> getByEspecialidad(String nombre){
        return (List<Subespecialidad>) subespecialidadCRUDRepository.findSubespecialidadByEspecialidad(nombre);
    }

}
