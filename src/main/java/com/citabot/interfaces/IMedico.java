package com.citabot.interfaces;

import com.citabot.model.Medico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IMedico extends CrudRepository<Medico, Integer> {

    List<Medico> findMedicoByNombre(String nombre);
    Medico findMedicoByUsuarioId(int usuarioId);

}
