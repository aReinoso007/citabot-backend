package com.citabot.interfaceService;

import com.citabot.model.Clinica;

import java.util.List;
import java.util.Optional;

public interface IClinicaService {

    public List<Clinica> listar();
    public List<Clinica> listarByNombre(String nombre);
    public Clinica save(Clinica clinica);
    public String delete(int id);
    public Optional<Clinica> findById(int id);
    public Clinica update(Clinica clinica);
    public List<Clinica> listarPorMedico(int idMedico);
    public List<Clinica> listarClinicasDisponiblesParaMedico(int medicoId);
    public List<Clinica> listarClinicasDisponiblesDiaMedico (int medioId, String dia);
}
