package com.citabot.interfaceService;

import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.formulario.FPaciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    public List<Paciente> listar();
    public Optional<Paciente> listarByNombre(String n);
    public Paciente findById(long id);
    public Paciente save(FPaciente form);
    public Paciente edit(Paciente paciente);
    public String delete(long id);
    public Optional<Paciente> listarByNombreYApellido(String n);
    public Paciente findByEmail(String email);
    public Paciente buscarPorEmailYContrasena(String email, String password);
    public Paciente update(long id, Paciente paciente);
    public Paciente buscarPorUsername(String username);
    public Boolean existeUsername(String username);
}
