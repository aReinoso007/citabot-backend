package com.citabot.persistence;

import com.citabot.persistence.crud.EspecialidadCRUDRepository;
import com.citabot.persistence.entity.Especialidad;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EspecialidadRepository {

    private EspecialidadCRUDRepository especialidadCRUDRepository;

    public List<Especialidad> getAll(){
        return (List<Especialidad>) especialidadCRUDRepository.findAll();
    }

    public List<Especialidad> getByNombre(String nombre){
        return (List<Especialidad>) especialidadCRUDRepository.findByNombre(nombre);
    }

    public Especialidad save(Especialidad especialidad){
        return especialidadCRUDRepository.save(especialidad);
    }

    public void delete(int id){
        especialidadCRUDRepository.deleteById(id);
    }

}
