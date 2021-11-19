package com.citabot.interfaces;

import com.citabot.model.Clinica;
import org.springframework.data.repository.CrudRepository;

public interface IClinica extends CrudRepository<Clinica, Integer> {
}
