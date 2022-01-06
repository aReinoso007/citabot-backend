package com.citabot.interfaces;


import com.citabot.model.MedicoSubespecialidad;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicoSubespecialidad extends CrudRepository<MedicoSubespecialidad, Integer> {

    @Query(value = "select medico_subespecialidad_id from medico_subespecialidad \n" +
            "where medico_id=?1 and subespecialidad_id=?2", nativeQuery = true)
    int getRegistroId(int medid, int subid);
}
