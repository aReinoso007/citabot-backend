package com.citabot.interfaceService;

import com.citabot.model.Especialidad;

import com.citabot.model.Subespecialidad;

import java.util.List;
import java.util.Optional;

public interface ISubespecialidadService {
    public List<Subespecialidad> listar();
    public List<Subespecialidad> listarByNombre(String n);
    public Optional<Subespecialidad> findById(int id);
    public List<Subespecialidad> listarByEspecialidad(String nombre);
    public Subespecialidad save(String nombre, int especialidad_id);
    public String delete(int id);
    public List<Subespecialidad> listarDisponiblesParaMedicoPorEspecialidad(int medId, int espId);
    public List<Subespecialidad> listarRegistradasPorMedicoYEspecialidad(int medId, int espId);
}
