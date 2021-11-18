package com.citabot.persistence;

import com.citabot.persistence.crud.EnfermedadCRUDRepository;
import com.citabot.persistence.entity.Enfermedad;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnfermedadRepository {

    private EnfermedadCRUDRepository enfermedadCRUDRepository;

    public List<Enfermedad> getAll(){
        return (List<Enfermedad>) enfermedadCRUDRepository.findAll();
    }

}
