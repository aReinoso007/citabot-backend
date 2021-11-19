package com.citabot.interfaces;

import com.citabot.model.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IPaciente extends CrudRepository<Paciente, Integer> {

    List<Paciente> findPacientesByNombre(String nombre);
    Optional<Paciente> findPacientesByNombreOrApellido(String nombre, String apellido);
    Paciente findPacienteByUsuarioId(int usuarioId);

}
