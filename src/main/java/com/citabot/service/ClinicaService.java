package com.citabot.service;

import com.citabot.interfaceService.IClinicaService;

import com.citabot.interfaces.IClinica;
import com.citabot.interfaces.IDireccion;
import com.citabot.model.Clinica;
import com.citabot.model.Direccion;
import com.citabot.model.DireccionClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaService implements IClinicaService {
    @Autowired
    private IClinica data;
    @Autowired
    private IDireccion direccionData;

    @Override
    @Transactional(readOnly = true)
    public List<Clinica> listar() {
        return (List<Clinica>) data.findAll();
    }

    @Override
    @Transactional(readOnly = true)
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
        try {
            data.deleteById(id);
        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Clinica> findById(int id) {
        return (Optional<Clinica>) data.findById(id);
    }

    @Override
    public Clinica update(Clinica clinica) {
        return data.save(clinica);
    }

}
