package com.citabot.service;

import com.citabot.interfaceService.IClinicaService;

import com.citabot.interfaces.IClinica;
import com.citabot.model.Clinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaService implements IClinicaService {
    @Autowired
    private IClinica data;

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
    @Transactional(readOnly = true)
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
    public Boolean delete(int id) {
        boolean b = true;
        try{
            data.deleteById(id);
            return b;
        }catch(Exception error){
            System.out.printf("Error deleting Clinic: ", error.getMessage());
            b= false;

        }
        return b;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Clinica> findById(int id) {
        return (Optional<Clinica>) data.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Clinica buscarPorId(int id) {
        return (Clinica) data.findClinicaByClinicaId(id);
    }
}
