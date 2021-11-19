package com.citabot.interfaceService;

import com.citabot.model.Clinica;

import java.util.List;

public interface IClinicaService {

    public List<Clinica> listar();
    public List<Clinica> listarByNombre(String nombre);
    public Clinica save(Clinica clinica);
    public String delete(int id);
}
