package com.citabot.interfaces;

import com.citabot.model.Horario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHorario extends CrudRepository<Horario, Integer> {

    List<Horario> findHorarioByRegistroClinica(int id);
    @Query(value = "select * from horario where registro_clinica_registro_clinica_id=:id", nativeQuery = true)
    List<Horario> buscarPorRegistro(int id);

    @Query(value = "SELECT DISTINCT dia FROM horario WHERE registro_clinica_registro_clinica_id=:id", nativeQuery = true)
    List<String> buscarDiasEnRegistro(int id);

    @Query(value = "SELECT dia, inicio, fin FROM horario WHERE registro_clinica_registro_clinica_id=:id order by\n" +
            "case\n" +
            "when dia='MONDAY' then 1\n" +
            "when dia='TUESDAY' then 2\n" +
            "when dia='WEDNESDAY' then 3\n" +
            "when dia='THURSDAY' then 4\n" +
            "when dia='FRIDAY' then 5\n" +
            "when dia='SATURDAY' then 6\n" +
            "when dia='SUNDAY' then 7\n" +
            "END ASC", nativeQuery = true)
    List<String> horarioOrdenadoPorRegistro(int id);

    @Query(value = "SELECT * FROM horario WHERE registro_clinica_registro_clinica_id=:id order by\n" +
            "case\n" +
            "when dia='MONDAY' then 1\n" +
            "when dia='TUESDAY' then 2\n" +
            "when dia='WEDNESDAY' then 3\n" +
            "when dia='THURSDAY' then 4\n" +
            "when dia='FRIDAY' then 5\n" +
            "when dia='SATURDAY' then 6\n" +
            "when dia='SUNDAY' then 7\n" +
            "END ASC", nativeQuery = true)
    List<Horario> horarioOrdenadoOBJ(int id);

}
