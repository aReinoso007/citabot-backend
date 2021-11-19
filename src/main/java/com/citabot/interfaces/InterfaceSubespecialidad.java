package com.citabot.interfaces;

import com.citabot.model.Subespecialidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterfaceSubespecialidad extends CrudRepository<Subespecialidad, Integer> {

    List<Subespecialidad> findSubespecialidadByEspecialidad(String especialidad);

}
