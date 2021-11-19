package com.citabot.interfaceService;

import com.citabot.model.Paciente;

import java.util.List;

public interface IPacienteService {
    public List<Paciente> listar();
    public List<Paciente> listarByNombre(String n);
    public Paciente findById(int id);
    public Paciente save(Paciente paciente);
    public Paciente edit(Paciente paciente);
    public String delete(int id);
}
