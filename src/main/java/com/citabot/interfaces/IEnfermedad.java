package com.citabot.interfaces;

import com.citabot.model.Enfermedad;
import org.springframework.data.repository.CrudRepository;

public interface IEnfermedad extends CrudRepository<Enfermedad, Integer> {
}
