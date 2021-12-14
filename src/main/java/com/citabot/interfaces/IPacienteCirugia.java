package com.citabot.interfaces;

import com.citabot.model.PacienteCirugia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteCirugia extends CrudRepository<PacienteCirugia, Integer> {

    @Query(value = "SELECT * FROM paciente_cirugia WHERE paciente_id=:id", nativeQuery = true)
    public Optional<PacienteCirugia> listarByPacienteId(long id);

}
