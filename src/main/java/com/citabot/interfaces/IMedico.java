package com.citabot.interfaces;

import com.citabot.model.Medico;
import com.citabot.model.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IMedico extends CrudRepository<Medico, Integer> {

    List<Medico> findMedicoByNombre(String nombre);


    @Query(value = "SELECT * FROM medico WHERE usuario_id= :usuarioId", nativeQuery = true)
    Medico findMedicoByUsuarioId(int usuarioId);

    @Query(value = "select nombre, apellido, usuario_id  from medico, medico_especialidad where\n" +
            "medico.usuario_id=medico_especialidad.medico_id and\n" +
            "medico_especialidad.especialidad_id=?1", nativeQuery = true)
    List<Medico> listarPorEspecialidadId(int especialidadId);


}
