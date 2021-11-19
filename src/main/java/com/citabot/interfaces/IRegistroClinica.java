package com.citabot.interfaces;

import com.citabot.model.RegistroClinica;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRegistroClinica extends CrudRepository<RegistroClinica, Integer> {

    List<RegistroClinica> findRegistroClinicaByMedico(int id);
    RegistroClinica findRegistroClinicaByRegistroClinicaId(int id);
}
