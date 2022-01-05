package com.citabot.interfaceService;

import com.citabot.model.PacientePatologia;
import com.citabot.model.formulario.FPatologia;

import java.util.List;
import java.util.Optional;

public interface IPacientePatologiaService {

    public List<PacientePatologia> listar();
    public List<PacientePatologia> listarByPacienteId(long id);
    public Optional<PacientePatologia> listarById(int id);
    public String delete(int id);
    public PacientePatologia save(String tipo, long pacienteId, int enfermedadId);
    public PacientePatologia update(FPatologia formulario);

}
