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

    @Query(value = "select * from medico_especialidad where medico_id=?1 and especialidad_id=?2", nativeQuery = true)
    int getRegistroId(int medId, int espId);

}
