package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Enfermedad;
import org.springframework.data.repository.CrudRepository;

public interface EnfermedadCRUDRepository extends CrudRepository<Enfermedad, Integer> {
}
