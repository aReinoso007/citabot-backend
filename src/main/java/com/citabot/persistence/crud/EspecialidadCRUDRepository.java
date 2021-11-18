package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Especialidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EspecialidadCRUDRepository extends CrudRepository<Especialidad, Integer> {

    List<Especialidad> findByNombre(String nombre);

}
