package com.citabot.interfaceService;

import com.citabot.model.PacientePatologia;

import java.util.List;
import java.util.Optional;

public interface IPacientePatologiaService {

    public List<PacientePatologia> listar();
    public Optional<PacientePatologia> listarByPacienteId(int id);
    public String delete(int id);

}
