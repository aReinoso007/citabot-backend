package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Horario;
import org.springframework.data.repository.CrudRepository;

public interface HorarioCRUDRepository extends CrudRepository<Horario, Integer> {
}
