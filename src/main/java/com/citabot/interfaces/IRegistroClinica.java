package com.citabot.interfaces;

import com.citabot.model.RegistroClinica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRegistroClinica extends CrudRepository<RegistroClinica, Integer> {

    List<RegistroClinica> findRegistroClinicaByMedico(int id);
    RegistroClinica findRegistroClinicaByRegistroClinicaId(int id);
    Optional<RegistroClinica> findRegistroClinicasByMedico(int id);
    @Query(value = "SELECT DISTINCT registro_clinica_id FROM registro_clinica where clinica_id= :idClinica and medico_id= :idMed", nativeQuery = true)
    Optional<Integer> getRegistroClinicaByClinicaAndMedico(int idClinica, int idMed);

}
