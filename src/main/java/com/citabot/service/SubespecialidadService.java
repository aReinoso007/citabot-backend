package com.citabot.service;

import com.citabot.interfaceService.ISubespecialidadService;
import com.citabot.interfaces.ISubespecialidad;
import com.citabot.interfaces.IEspecialidad;
import com.citabot.model.Especialidad;
import com.citabot.model.Subespecialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubespecialidadService implements ISubespecialidadService {

    @Autowired
    private ISubespecialidad data;

    @Autowired
    private IEspecialidad dataespecialidad;


    @Override
    public List<Subespecialidad> listar() {
        return (List<Subespecialidad>) data.findAll();
    }

    @Override
    public List<Subespecialidad> listarByNombre(String n) {
        return null;
    }

    @Override
    public Optional<Subespecialidad> findById(int id) {
        return data.findById(id);
    }

    @Override
    public List<Subespecialidad> listarByEspecialidad(String nombre) {
        return data.findSubespecialidadByEspecialidad(nombre);
    }

    @Override
    public Subespecialidad save(String nombre, int especialidad_id) {
        Subespecialidad subespd = new Subespecialidad();
        Especialidad especialidad = new Especialidad();
        Subespecialidad subespecialidad = new Subespecialidad();
        try {
            if (dataespecialidad.existsById(especialidad_id)){
                especialidad = dataespecialidad.findById(especialidad_id).get();
                subespecialidad.setNombre(nombre);
                subespecialidad.setEspecialidad(especialidad);
                subespd=data.save(subespecialidad);
                return subespd;
            }else {
                return null;
            }


        }catch (Error e){
            System.out.printf("Error insertando: ", e.getCause());
        }

        return subespd;
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

    @Override
    public List<Subespecialidad> listarDisponiblesParaMedicoPorEspecialidad(int medId, int espId) {
        return data.listarDisponibles(medId, espId);
    }

    @Override
    public List<Subespecialidad> listarRegistradasPorMedicoYEspecialidad(int medId, int espId) {
        return data.listarTodasRegistradasPorMedico(medId, espId);
    }
}
