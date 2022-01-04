package com.citabot.interfaces;

import com.citabot.model.Subespecialidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISubespecialidad extends CrudRepository<Subespecialidad, Integer> {

    List<Subespecialidad> findSubespecialidadByEspecialidad(String especialidad);
    @Query(value = "select * from subespecialidad\n" +
            "where not exists \n" +
            "(select subespecialidad_id from medico_subespecialidad \n" +
            " where medico_id=:medId and subespecialidad_id=subespecialidad.subespecialidad_id)\n" +
            " and especialidad_id=:espId", nativeQuery = true)
    List<Subespecialidad> listarDisponibles(int medId, int espId);

    @Query(value = "select * from subespecialidad\n" +
            "where subespecialidad_id in (SELECT subespecialidad_id  from medico_subespecialidad where medico_id=:medId and especialidad_id=:espId)", nativeQuery = true)
    List<Subespecialidad> listarTodasRegistradasPorMedico(int medId, int espId);
}
