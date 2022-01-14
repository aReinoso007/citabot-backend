package com.citabot.interfaces;

import com.citabot.model.Cita;
import com.citabot.model.formulario.interfaces.CitaConstl;
import com.citabot.model.formulario.interfaces.CitaDets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    @Query(value = "SELECT DISTINCT fecha_cita from cita where registro_clinica_id=:id and fecha_cita>:ts and estado = 'pendiente' order by fecha_cita asc", nativeQuery = true)
    List<String> getFechasCitaPorRegistro(int id, Timestamp ts);

    @Query(value = "SELECT DISTINCT fecha_cita from cita where fecha_cita>:ts order by fecha_cita asc", nativeQuery = true)
    List<String> getDiasCitaPorRegistro(Timestamp ts);

    @Query(value = "SELECT DISTINCT fecha_cita from cita, registro_clinica where \n" +
            "registro_clinica.registro_clinica_id=cita.registro_clinica_id and \n" +
            "cita.fecha_cita>:ts and\n" +
            "registro_clinica.medico_id=:idMedico and \n" +
            "cita.estado = 'pendiente' \n" +
            "order by fecha_cita asc", nativeQuery = true)
    List<String> getDiasCitaPorMedico(Timestamp ts, int idMedico );

    @Query(value = "SELECT DISTINCT fecha_cita from cita, registro_clinica where\n" +
            "            registro_clinica.registro_clinica_id=cita.registro_clinica_id and \n" +
            "            cita.fecha_cita>:ts and\n" +
            "            registro_clinica.clinica_id=:idClinica and \n" +
            "\t\t\tregistro_clinica.medico_id=:idMedico and\n" +
            "            cita.estado = 'pendiente' \n" +
            "            order by fecha_cita asc", nativeQuery = true)
    List<String> getDiasCitaPorClinica (Timestamp ts, int idClinica, int idMedico);

    @Query(value = "select cita.cita_id as citaId, cita.fecha_cita as fechaCita, cita.sintomas as sintomas, registro_clinica.clinica_id as clinicaId, registro_clinica.medico_id as medicoId from cita, paciente, registro_clinica where\n" +
            "            cita.registro_clinica_id=registro_clinica.registro_clinica_id and\n" +
            "            cita.paciente_id=paciente.usuario_id and paciente.usuario_id=?1 and cita.estado='pendiente' order by cita.created_at desc", nativeQuery = true)
    List<CitaConstl> listarCitaPorPacienteId(int pacienteId);

    @Query(value = "select cita.cita_id as citaId, cita.fecha_cita as fechaCita, cita.sintomas as sintomas, registro_clinica.clinica_id as clinicaId, registro_clinica.medico_id as medicoId from cita, paciente, registro_clinica where\n" +
            "            cita.registro_clinica_id=registro_clinica.registro_clinica_id and\n" +
            "            cita.paciente_id=paciente.usuario_id and cita.cita_id=?1 and cita.estado='pendiente'", nativeQuery = true)
    List<CitaConstl> getCitaById(int citaId);

    @Query(value = "select * from cita\n" +
            "where registro_clinica_id in (SELECT registro_clinica_id  from registro_clinica where medico_id=:id) order by fecha_cita asc", nativeQuery = true)
    List<Cita> getAllCitasByMedicoId(int id);

    @Query(value = "select * from cita\n" +
            "where registro_clinica_id in (SELECT registro_clinica_id  from registro_clinica where medico_id=:id)\n" +
            "and fecha_cita >= current_date\n" +
            "order by fecha_cita asc;", nativeQuery = true)
    List<Cita> getTodayCitas(long id);

    @Query(value = "select cita.cita_id as citaId, cita.fecha_cita as fechaCita, cita.sintomas as sintomas, \n" +
            "registro_clinica.clinica_id as clinicaId, cita.paciente_id as pacienteId, cita.estado as estado from cita, paciente, registro_clinica \n" +
            "where\n" +
            "registro_clinica.registro_clinica_id=cita.registro_clinica_id and registro_clinica.medico_id=?1\n" +
            "and cita.fecha_cita >= current_date\n" +
            "order by cita.fecha_cita asc", nativeQuery = true)
    List<CitaDets> getCitasHoyDateComoString(int medId);

    @Query(value = "select cita.cita_id as citaId, cita.fecha_cita as fechaCita, cita.sintomas as sintomas, \n" +
            "registro_clinica.clinica_id as clinicaId, cita.paciente_id as pacienteId, cita.estado as estado from cita, paciente, registro_clinica \n" +
            "where\n" +
            "registro_clinica.registro_clinica_id=cita.registro_clinica_id and registro_clinica.medico_id=?1\n" +
            "order by cita.fecha_cita asc", nativeQuery = true)
    List<CitaDets> getHistorialMedicoFechaString(int medId);

}
