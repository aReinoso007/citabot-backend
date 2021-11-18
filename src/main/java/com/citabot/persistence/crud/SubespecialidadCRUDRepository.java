package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Subespecialidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubespecialidadCRUDRepository extends CrudRepository<Subespecialidad, Integer> {

    List<Subespecialidad> findSubespecialidadByEspecialidad(String especialidad);

}
