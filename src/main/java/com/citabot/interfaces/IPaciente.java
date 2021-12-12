package com.citabot.interfaces;

import com.citabot.model.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IPaciente extends CrudRepository<Paciente, Integer> {

    Optional<Paciente> findPacientesByNombre(String nombre);
    Optional<Paciente> findPacientesByNombreOrApellido(String nombre, String apellido);

    @Query(value = "SELECT * FROM paciente WHERE usuario_id= :usuarioId", nativeQuery = true)
    Optional<Paciente> findPacienteByUsuarioId(int usuarioId);
    Optional<Paciente> findPacienteByEmail(String email);
    Paciente findPacienteByEmailAndPassword(String email, String password);
    Paciente findPacienteByUsername(String username);
    Boolean existsByUsername(String username);
    Paciente findPacienteByUsernameAndPassword(String username, String password);

}
