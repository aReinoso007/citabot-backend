package com.citabot.persistence;

import com.citabot.persistence.crud.EspecialidadCRUDRepository;
import com.citabot.persistence.entity.Especialidad;

import java.util.List;

public class EspecialidadRepository {

    private EspecialidadCRUDRepository especialidadCRUDRepository;

    public List<Especialidad> getAll(){
        return (List<Especialidad>) especialidadCRUDRepository.findAll();
    }

}
