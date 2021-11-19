package com.citabot.interfaces;

import com.citabot.model.RegistroClinica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRegistroClinica extends CrudRepository<RegistroClinica, Integer> {

    List<RegistroClinica> findRegistroClinicaByMedico(int id);
    RegistroClinica findRegistroClinicaByRegistroClinicaId(int id);
}
