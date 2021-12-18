package com.citabot.interfaces;

import com.citabot.model.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Query(value = "select * from cita\n" +
            "where registro_clinica_id in (SELECT registro_clinica_id  from registro_clinica where medico_id=:id order by fecha_cita asc", nativeQuery = true)
    List<Cita> getAllCitasByMedicoId(long id);
    @Query(value = "select * from cita\n" +
            "where registro_clinica_id in (SELECT registro_clinica_id  from registro_clinica where medico_id=:id)\n" +
            "and fecha_cita = now()\n" +
            "order by fecha_cita asc;", nativeQuery = true)
    List<Cita> getTodayCitas(long id);
}
