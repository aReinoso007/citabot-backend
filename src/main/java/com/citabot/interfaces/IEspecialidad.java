package com.citabot.interfaces;

import com.citabot.model.Especialidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEspecialidad extends CrudRepository<Especialidad, Integer> {

    List<Especialidad> findByNombre(String nombre);

}