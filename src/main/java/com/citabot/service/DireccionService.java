package com.citabot.service;

import com.citabot.interfaceService.IDireccionService;
import com.citabot.interfaces.IDireccion;
import com.citabot.model.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService implements IDireccionService {

    @Autowired
    private IDireccion data;


    @Override
    public List<Direccion> listar() {
        return (List<Direccion>) data.findAll();
    }

    @Override
    public Direccion save(Direccion direccion) {

        Direccion d = new Direccion();
        try{
            d = data.save(direccion);

        }catch (Error error){
            System.out.printf("Error saving: ", error.getMessage());
        }

        return d;
    }

    @Override
    public Direccion update(Direccion direccion) {
        Direccion d = new Direccion();
        try{
            d = data.findById(direccion.getDireccionId()).get();
            d = data.save(direccion);
        }catch (Error error){
            System.out.printf("Error updating: ", error.getMessage());
        }
        return d;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Direccion> findById(int id) {
        return (Optional<Direccion>) data.findById(id);
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try {
            if(data.existsById(id)){
                data.deleteById(id);

            }else{
                message = "FAILED: NO RECORD MATCHES THE PROVIDED ID";
            }
        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
        }
        return message;
    }
}
