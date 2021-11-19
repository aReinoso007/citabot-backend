package com.citabot.service;

import com.citabot.interfaceService.ISubespecialidadService;
import com.citabot.interfaces.ISubespecialidad;
import com.citabot.model.Especialidad;
import com.citabot.model.Subespecialidad;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubespecialidadService implements ISubespecialidadService {

    @Autowired
    private ISubespecialidad data;


    @Override
    public List<Subespecialidad> listar() {
        return (List<Subespecialidad>) data.findAll();
    }

    @Override
    public List<Subespecialidad> listarByNombre(String n) {
        return null;
    }

    @Override
    public List<Subespecialidad> listarByEspecialidad(String nombre) {
        return (List<Subespecialidad>) data.findSubespecialidadByEspecialidad(nombre);
    }

    @Override
    public Subespecialidad save(Subespecialidad subespecialidad, Especialidad especialidad) {
        return data.save(subespecialidad);
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try{
            data.deleteById(id);
        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }
}
