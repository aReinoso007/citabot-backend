package com.citabot.interfaceService;

import com.citabot.model.DireccionPaciente;

import java.util.List;
import java.util.Optional;

public interface IDireccionPacienteService {

    public List<DireccionPaciente> listar();
    public Optional<DireccionPaciente> listarByPacienteId(int id);
    public String delete(int id);

}
