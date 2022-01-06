package com.citabot.interfaces;

import com.citabot.model.Especialidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEspecialidad extends CrudRepository<Especialidad, Integer> {

    List<Especialidad> findByNombre(String nombre);
    @Query(value = "select * from especialidad\n" +
            "WHERE NOT EXISTS (SELECT especialidad_id from medico_especialidad where medico_id=:id and especialidad_id = especialidad.especialidad_id)", nativeQuery = true)
    List<Especialidad> listarEspecialidadesDisponibles(int id);

    @Query(value = "select * from especialidad\n" +
            "where especialidad_id in (SELECT especialidad_id  from medico_especialidad where medico_id=:medId )", nativeQuery = true)
    List<Especialidad> listarEspecialidadesRegistradasPorMedico(int medId);

    @Query(value = "select DISTINCT ON (especialidad.nombre) * \n" +
            "from horario, registro_clinica,medico_especialidad, especialidad where \n" +
            "horario.dia=:dia and\n" +
            "horario.registro_clinica_registro_clinica_id=registro_clinica.registro_clinica_id and \n" +
            "medico_especialidad.medico_id=registro_clinica.medico_id and\n" +
            "medico_especialidad.especialidad_id=especialidad.especialidad_id", nativeQuery = true)
    List<Especialidad> listarEspecialidadesPorDia(String dia);
}
