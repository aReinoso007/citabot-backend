package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Clinica;
import org.springframework.data.repository.CrudRepository;

public interface ClinicaCRUDRepository extends CrudRepository<Clinica, Integer> {
}
