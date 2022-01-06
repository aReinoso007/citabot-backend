package com.citabot.interfaces;

import com.citabot.model.Medico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IMedico extends CrudRepository<Medico, Long> {

    List<Medico> findMedicoByNombre(String nombre);


    @Query(value = "SELECT * FROM medico WHERE usuario_id= :usuarioId", nativeQuery = true)
    Medico findMedicoByUsuarioId(long usuarioId);
    Optional<Medico> findMedicoByEmail(String email);
    Medico findMedicoByEmailAndPassword(String email, String password);

    @Query(value = "select * from medico, medico_especialidad where\n" +
            "medico.usuario_id=medico_especialidad.medico_id and\n" +
            "medico_especialidad.especialidad_id=?1", nativeQuery = true)
    List<Medico> listarPorEspecialidadId(int especialidadId);

    @Query(value = "select * from horario, registro_clinica, medico, medico_especialidad\n" +
            "where horario.dia=:dia and\n" +
            "horario.registro_clinica_registro_clinica_id=registro_clinica.registro_clinica_id and \n" +
            "registro_clinica.medico_id=medico.usuario_id and\n" +
            "medico_especialidad.medico_id = medico.usuario_id and\n" +
            "medico_especialidad.especialidad_id=:especialidadId", nativeQuery = true)
    List<Medico> listarPorDiaEspecilaidad(int especialidadId, String dia);

    Medico findMedicoByUsername(String username);
    Boolean existsByUsername(String username);
    Medico findMedicoByUsernameAndPassword(String username, String password);

    @Query(value = "select * from clinica, registro_clinica, medico where\n" +
            "clinica.clinica_id=registro_clinica.clinica_id and\n" +
            "medico.usuario_id=registro_clinica.medico_id and\n" +
            "clinica.clinica_id=?1", nativeQuery = true)
    List<Medico> listarPorClinica(int clinicaId);


}
