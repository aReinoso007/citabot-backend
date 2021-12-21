package com.citabot.service;

import com.citabot.interfaceService.IEspecialidadService;
import com.citabot.interfaces.IEspecialidad;
import com.citabot.model.Enfermedad;
import com.citabot.model.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService implements IEspecialidadService {
    @Autowired
    private IEspecialidad data;
    @Override
    public List<Especialidad> listar() {
        return (List<Especialidad>) data.findAll();
    }

    @Override
    public Optional<Especialidad> findById(int id) {
        return (Optional<Especialidad>) data.findById(id);
    }


    @Override
    public List<Especialidad> listarByNombre(String nombre) {
        return (List<Especialidad>) data.findByNombre(nombre);
    }

    @Override
    public Especialidad save(Especialidad especialidad) {
        Especialidad e = new Especialidad();
        try{
            e = data.save(especialidad);
        }catch (Error error){
            System.out.printf("Error saving EspecialidadService.save: ", error.getMessage());
        }
        return e;
    }

    @Override
    public Especialidad edit(Especialidad especialidad) {
        Especialidad e = new Especialidad();
        try{
            e = data.save(especialidad);
        }catch (Error error){
            System.out.printf("Error updating EspecialidadService.edit: ", error.getMessage());
        }
        return e;
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try {
            data.deleteById(id);
        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }

    @Override
    public Especialidad update(Especialidad especialidad) {
        return data.save(especialidad);
    }

    @Override
    public List<Especialidad> listarDisponiblesParaMedico(int medicoId) {
        return data.listarEspecialidadesDisponibles(medicoId);
    }
}
