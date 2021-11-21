package com.citabot.interfaces;

import com.citabot.model.DireccionClinica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDireccionClinica extends CrudRepository<DireccionClinica, Integer> {

    @Query(value = "SELECT * FROM direccion_clinica WHERE clinica_id=:id", nativeQuery = true)
    public Optional<DireccionClinica> listarDireccionesByClinicaId(int id);

}
