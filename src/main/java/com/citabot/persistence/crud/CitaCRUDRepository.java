package com.citabot.persistence.crud;

import com.citabot.persistence.entity.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CitaCRUDRepository extends CrudRepository<Cita, Integer> {


    /*Encontrar citas de paciente */
    /*@Query(value = "SELECT * FROM cita WHERE paciente_id =?", nativeQuery = true)*/
    //List<Cita> findCitaByPacienteAndClinicaMedicoOrderByAsc(int pacienteId, int clinicaMedico);

    Optional<List<Cita>> findCitaByClinicaMedicoAndEstado(int idClinica, String estado);
}
