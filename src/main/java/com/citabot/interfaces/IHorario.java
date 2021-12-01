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
}
