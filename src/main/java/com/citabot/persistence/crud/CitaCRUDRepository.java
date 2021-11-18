package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Cita;
import org.springframework.data.repository.CrudRepository;

public interface CitaCRUDRepository extends CrudRepository<Cita, Integer> {
}
