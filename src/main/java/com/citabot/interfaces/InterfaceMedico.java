package com.citabot.interfaces;

import com.citabot.model.Medico;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceMedico extends CrudRepository<Medico, Integer> {
}
