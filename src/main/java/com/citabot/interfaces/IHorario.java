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
    /*Esta funcion sirve para generar las fechas en un rango de 7 dias */
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


    @Query(value = "SELECT dia, inicio, fin FROM horario WHERE \n" +
            "horario.dia=:dia and\n" +
            "registro_clinica_registro_clinica_id=:idRegistroClinica order by\n" +
            "            case\n" +
            "            when dia='MONDAY' then 1\n" +
            "            when dia='TUESDAY' then 2\n" +
            "            when dia='WEDNESDAY' then 3\n" +
            "            when dia='THURSDAY' then 4\n" +
            "            when dia='FRIDAY' then 5\n" +
            "            when dia='SATURDAY' then 6\n" +
            "            when dia='SUNDAY' then 7\n" +
            "            END ASC", nativeQuery = true)
    List<String> horarioOrdenadoPorDia(int idRegistroClinica, String dia);


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

    @Query(value = "SELECT dia, inicio, fin FROM horario  order by\n" +
            "case\n" +
            "when dia='MONDAY' then 1\n" +
            "when dia='TUESDAY' then 2\n" +
            "when dia='WEDNESDAY' then 3\n" +
            "when dia='THURSDAY' then 4\n" +
            "when dia='FRIDAY' then 5\n" +
            "when dia='SATURDAY' then 6\n" +
            "when dia='SUNDAY' then 7\n" +
            "END ASC", nativeQuery = true)
    List<String> diasOrdenadosPorRegistro();



    @Query(value = "SELECT dia, inicio, fin FROM horario, registro_clinica, medico_especialidad WHERE \n" +
            " horario.dia=:dia and\n" +
            " medico_especialidad.especialidad_id=:idEspecialidad and\n" +
            " horario.registro_clinica_registro_clinica_id= registro_clinica.registro_clinica_id and\n" +
            " registro_clinica.medico_id=:idMedico order by\n" +
            "            case\n" +
            "            when dia='MONDAY' then 1\n" +
            "            when dia='TUESDAY' then 2\n" +
            "            when dia='WEDNESDAY' then 3\n" +
            "            when dia='THURSDAY' then 4\n" +
            "            when dia='FRIDAY' then 5\n" +
            "            when dia='SATURDAY' then 6\n" +
            "            when dia='SUNDAY' then 7\n" +
            "            END ASC", nativeQuery = true)
    List<String> diasOrdenadosPorDiaMedico(int idEspecialidad, int idMedico ,String dia);

    @Query(value = "SELECT dia, inicio, fin FROM horario, registro_clinica WHERE \n" +
            "            horario.dia=:dia and\n" +
            "            horario.registro_clinica_registro_clinica_id= registro_clinica.registro_clinica_id and\n" +
            "            registro_clinica.medico_id=:idMedico and\n" +
            "\t\t\tregistro_clinica.clinica_id=:idClinica order by\n" +
            "            case\n" +
            "            when dia='MONDAY' then 1\n" +
            "            when dia='TUESDAY' then 2\n" +
            "            when dia='WEDNESDAY' then 3\n" +
            "            when dia='THURSDAY' then 4\n" +
            "            when dia='FRIDAY' then 5\n" +
            "            when dia='SATURDAY' then 6\n" +
            "            when dia='SUNDAY' then 7\n" +
            "            END ASC", nativeQuery = true)
    List<String> diasOrdenadosPorDiaClinica(int idClinica,  int idMedico ,String dia);

}

