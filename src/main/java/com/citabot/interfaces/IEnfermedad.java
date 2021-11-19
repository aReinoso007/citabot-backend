package com.citabot.interfaces;

import com.citabot.model.Enfermedad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEnfermedad extends CrudRepository<Enfermedad, Integer> {
    List<Enfermedad> findEnfermedadByNombre(String nombre);
}
