package com.citabot.interfaces;

import com.citabot.model.Cita;
import com.citabot.model.formulario.interfaces.CitaConstl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICita extends CrudRepository<Cita, Integer> {



    @Query(value = "SELECT * FROM cita WHERE paciente_usuario_id=:pacienteId and estado=:estado", nativeQuery = true)
    Optional<Cita> findByPacienteAndEstado(int pacienteId, String estado);
    @Query(value = "SELECT * FROM cita WHERE paciente_usuario_id=:id", nativeQuery = true)
    List<Cita> getCitasByPacienteId(int id);

    @Query(value = "SELECT * FROM cita WHERE registro_clinica_id=:id ORDER BY fecha_cita ASC", nativeQuery = true)
    List<Cita> getCitasByRegistroId(int id);

    @Query(value = "SELECT DISTINCT fecha_cita from cita where registro_clinica_id=:id order by fecha_cita asc", nativeQuery = true)
    List<String> getFechasCitaPorRegistro(int id);

    @Query(value = "select cita.cita_id as citaId, cita.fecha_cita as fechaCita, cita.sintomas as sintomas, registro_clinica.clinica_id as clinicaId, registro_clinica.medico_id as medicoId from cita, paciente, registro_clinica where\n" +
            "cita.registro_clinica_id=registro_clinica.registro_clinica_id and\n" +
            "cita.paciente_id=paciente.usuario_id and paciente.usuario_id=?1", nativeQuery = true)
    List<CitaConstl> listarCitaPorPacienteId(int pacienteId);


}
