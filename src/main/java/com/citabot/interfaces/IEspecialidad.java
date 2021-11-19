package com.citabot.interfaces;

import com.citabot.model.Especialidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEspecialidad extends CrudRepository<Especialidad, Integer> {

    List<Especialidad> findByNombre(String nombre);

}
