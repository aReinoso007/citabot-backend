package com.citabot.interfaces;

import com.citabot.model.DireccionPaciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDireccionPaciente extends CrudRepository<DireccionPaciente, Integer> {

    @Query(value = "SELECT * FROM direccion_paciente WHERE paciente_id=:id", nativeQuery = true)
    public Optional<DireccionPaciente> listarByPacienteId(int id);

    public Optional<DireccionPaciente> findAllByDireccionPacienteId(int id);

}
