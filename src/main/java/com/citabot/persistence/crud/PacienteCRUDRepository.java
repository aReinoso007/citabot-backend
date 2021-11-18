package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteCRUDRepository extends CrudRepository<Paciente, Integer> {
}
