package com.citabot.service;

import com.citabot.interfaceService.IEnfermedadService;
import com.citabot.interfaces.IEnfermedad;
import com.citabot.model.Enfermedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Enfermedad> findById(int id) {
        return (Optional<Enfermedad>) data.findById(id);
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
            if(data.existsById(enfermedad.getEnfermedadId())){
                e = data.save(enfermedad);
                return e;
            }else{
                return null;
            }

        }catch (Error error){
            System.out.printf("Error updating EnfermedadService: ", error.getMessage());
        }
        return e;
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try {
            if(!data.existsById(id)){
                message = "Registro no existe";
            }else{
                data.deleteById(id);
            }

        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }
}
