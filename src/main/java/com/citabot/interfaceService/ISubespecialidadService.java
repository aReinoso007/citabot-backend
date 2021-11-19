package com.citabot.interfaceService;

import com.citabot.model.Especialidad;
import com.citabot.model.Subespecialidad;

import java.util.List;

public interface ISubespecialidadService {
    public List<Subespecialidad> listar();
    public List<Subespecialidad> listarByNombre(String n);
    public List<Subespecialidad> listarByEspecialidadId(int id);
    public Subespecialidad save(Subespecialidad subespecialidad, Especialidad especialidad);
    public String delete(int id);
}
