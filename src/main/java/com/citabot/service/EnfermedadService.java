package com.citabot.service;

import com.citabot.interfaceService.IEnfermedadService;
import com.citabot.interfaces.IEnfermedad;
import com.citabot.model.Enfermedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnfermedadService implements IEnfermedadService {
    @Autowired
    private IEnfermedad data;

    @Override
    public List<Enfermedad> listar() {
        return (List<Enfermedad>) data.findAll();
    }

    @Override
    public List<Enfermedad> listarByNombre(String nombre) {
        return (List<Enfermedad>) data.findEnfermedadByNombre(nombre);
    }

    @Override
    public Enfermedad save(Enfermedad enfermedad) {
        Enfermedad e = new Enfermedad();
        try{
            e = data.save(enfermedad);
        }catch (Error error){
            System.out.printf("Error saving Disease: ", error.getMessage());
        }
        return e;
    }

    @Override
    public Enfermedad edit(Enfermedad enfermedad) {
        Enfermedad e = new Enfermedad();
        try{
            e = data.save(enfermedad);
        }catch (Error error){
            System.out.printf("Error updating EnfermedadService: ", error.getMessage());
        }
        return e;
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
