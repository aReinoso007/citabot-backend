package com.citabot.interfaces;

import com.citabot.model.PacientePatologia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPacientePatologia extends CrudRepository<PacientePatologia, Integer> {

    @Query(value = "SELECT * FROM paciente_patologia WHERE paciente_id=:id", nativeQuery = true)
    public List<PacientePatologia> listarByPacienteId(long id);
    


}
