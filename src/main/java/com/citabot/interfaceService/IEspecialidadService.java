package com.citabot.interfaceService;

import com.citabot.model.Enfermedad;
import com.citabot.model.Especialidad;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadService {

    public List<Especialidad> listar();
    public List<Especialidad> listarByNombre(String nombre);
    public Optional<Especialidad> findById(int id);
    public Especialidad save(Especialidad especialidad);
    public Especialidad edit(Especialidad especialidad);
    public String delete(int id);
}
