package com.citabot.interfaces;

import com.citabot.model.Cita;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ICita extends CrudRepository<Cita, Integer> {


    /*Encontrar citas de paciente */
    /*@Query(value = "SELECT * FROM cita WHERE paciente_id =?", nativeQuery = true)*/
    //List<Cita> findCitaByPacienteAndClinicaMedicoOrderByAsc(int pacienteId, int clinicaMedico);

    List<Cita> findCitaByPaciente(int id);
}