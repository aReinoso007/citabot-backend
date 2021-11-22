package com.citabot.interfaces;

import com.citabot.model.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICita extends CrudRepository<Cita, Integer> {



    @Query(value = "SELECT * FROM cita WHERE paciente_usuario_id=:pacienteId and estado=:estado", nativeQuery = true)
    Optional<Cita> findByPacienteAndEstado(int pacienteId, String estado);
    Optional<Cita> findCitaByPaciente(int id);
}
