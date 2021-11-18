package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Medico;
import org.springframework.data.repository.CrudRepository;

public interface MedicoCRUDRepository extends CrudRepository<Medico, Integer> {
}
