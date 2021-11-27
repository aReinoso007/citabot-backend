package com.citabot.interfaces;


import com.citabot.model.MedicoSubespecialidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicoSubespecialidad extends CrudRepository<MedicoSubespecialidad, Integer> {
}
