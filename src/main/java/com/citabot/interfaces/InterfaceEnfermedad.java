package com.citabot.interfaces;

import com.citabot.model.Enfermedad;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceEnfermedad extends CrudRepository<Enfermedad, Integer> {
}
