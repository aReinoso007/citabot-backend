package com.citabot.interfaces;

import com.citabot.model.MedicoEspecialidad;
import com.citabot.model.RegistroClinica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMedicoEspecialidad extends CrudRepository<MedicoEspecialidad, Integer> {


}
