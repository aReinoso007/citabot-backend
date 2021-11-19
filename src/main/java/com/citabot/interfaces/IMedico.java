package com.citabot.interfaces;

import com.citabot.model.Medico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IMedico extends CrudRepository<Medico, Integer> {

    List<Medico> findMedicoByNombre(String nombre);
    Medico findMedicoById(int id);

}
