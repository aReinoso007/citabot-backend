package com.citabot.interfaces;

import com.citabot.model.Horario;
import org.springframework.data.repository.CrudRepository;

public interface IHorario extends CrudRepository<Horario, Integer> {
}
