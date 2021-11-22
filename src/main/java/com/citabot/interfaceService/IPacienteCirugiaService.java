package com.citabot.interfaceService;

import com.citabot.model.PacienteCirugia;

import java.util.List;
import java.util.Optional;

public interface IPacienteCirugiaService {
    public List<PacienteCirugia> listar();
    public Optional<PacienteCirugia> listarById(int id);
    public PacienteCirugia save(int pacId, int cirId, String tipo);
    public Optional<PacienteCirugia> listarByPacienteId(int id);
    public String delete(int id);

}
