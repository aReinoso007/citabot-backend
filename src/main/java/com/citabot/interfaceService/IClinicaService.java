package com.citabot.interfaceService;

import com.citabot.model.Clinica;

import java.util.List;
import java.util.Optional;

public interface IClinicaService {

    public List<Clinica> listar();
    public List<Clinica> listarByNombre(String nombre);
    public Clinica save(Clinica clinica);
    public Boolean delete(int id);
    public Optional<Clinica> findById(int id);
    public Clinica buscarPorId(int id);
}
