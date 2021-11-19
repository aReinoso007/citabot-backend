package com.citabot.interfaces;

import com.citabot.model.Horario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IHorario extends CrudRepository<Horario, Integer> {

    List<Horario> findHorarioByRegistroClinica(int id);
}
