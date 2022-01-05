package com.citabot.interfaceService;

import com.citabot.model.PacienteCirugia;
import com.citabot.model.formulario.FCirugia;

import java.util.List;
import java.util.Optional;

public interface IPacienteCirugiaService {
    public List<PacienteCirugia> listar();
    public Optional<PacienteCirugia> listarById(int id);
    public PacienteCirugia save(long pacId, int cirId, String tipo);
    public Optional<PacienteCirugia> listarByPacienteId(long id);
    public String delete(int id);
    public  PacienteCirugia update(FCirugia formulario);

}
