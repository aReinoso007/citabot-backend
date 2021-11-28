package com.citabot.interfaceService;

import com.citabot.model.Cita;
import com.citabot.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    public List<Paciente> listar();
    public Optional<Paciente> listarByNombre(String n);
    public Paciente findById(int id);
    public Paciente save(Paciente paciente);
    public Paciente edit(Paciente paciente);
    public String delete(int id);
    public Optional<Paciente> listarByNombreYApellido(String n);
    public Paciente findByEmail(String email);
    public Paciente buscarPorEmailYContrasena(String email, String password);
    public Paciente update(int id, Paciente paciente);
}
