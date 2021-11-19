package com.citabot.interfaces;

import com.citabot.model.Enfermedad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEnfermedad extends CrudRepository<Enfermedad, Integer> {
    List<Enfermedad> findEnfermedadByNombre(String nombre);
}
