package com.citabot.interfaceService;

import com.citabot.model.Medico;

import java.util.List;

public interface IMedicoService {
    public List<Medico> listar();
    public List<Medico> listarByName(String name);
    public Medico findById(int id);
    public Medico save(Medico medico);
    public Medico edit(Medico medico);
    public String delete(int id);
}
