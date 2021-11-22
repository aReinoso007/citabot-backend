package com.citabot.interfaceService;

import com.citabot.model.PacientePatologia;

import java.util.List;
import java.util.Optional;

public interface IPacientePatologiaService {

    public List<PacientePatologia> listar();
    public List<PacientePatologia> listarByPacienteId(int id);
    public Optional<PacientePatologia> listarById(int id);
    public String delete(int id);
    public PacientePatologia save(String tipo, int pacienteId, int enfermedadId);

}
