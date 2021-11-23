package com.citabot.interfaceService;

import com.citabot.model.Especialidad;

import java.util.List;

public interface IEspecialidadService {

    public List<Especialidad> listar();
    public List<Especialidad> listarByNombre(String nombre);
    public Especialidad save(Especialidad especialidad);
    public Especialidad edit(Especialidad especialidad);
    public String delete(int id);
    public Especialidad update(Especialidad especialidad);
}
