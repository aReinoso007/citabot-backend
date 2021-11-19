package com.citabot.service;

import com.citabot.interfaceService.IClinicaService;

import com.citabot.interfaces.IClinica;
import com.citabot.model.Clinica;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClinicaService implements IClinicaService {
    @Autowired
    private IClinica data;

    @Override
    public List<Clinica> listar() {
        return (List<Clinica>) data.findAll();
    }

    @Override
    public List<Clinica> listarByNombre(String nombre) {
        return (List<Clinica>) data.findClinicaByNombreClinica(nombre);
    }

    @Override
    public Clinica save(Clinica clinica) {
        Clinica c = new Clinica();
        try {
            c = data.save(clinica);
        }catch (Error e){
            System.out.printf("Error saving Clinic: ", e.getMessage());
        }
        return c;
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try{
            data.deleteById(id);
        }catch(Error error){
            message = "FAILED";
            System.out.printf("Error deleting Clinic: ", error.getMessage());
        }
        return message;
    }
}
