package com.citabot.interfaces;

import com.citabot.model.Horario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IHorario extends CrudRepository<Horario, Integer> {

    List<Horario> findHorarioByRegistroClinica(int id);
}
