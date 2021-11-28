package com.citabot.interfaceService;

import com.citabot.model.Medico;

import java.util.List;
import java.util.Optional;

public interface IMedicoService {
    public List<Medico> listar();
    public List<Medico> listarByName(String name);
    public Optional<Medico> findById(int id);
    public Medico save(Medico medico);
    public Medico edit(Medico medico);
    public String delete(int id);
    public Medico buscarPorId(int id);
    public List<Medico> Listar_medicos_especialidad(int idEspecialidad);
}
