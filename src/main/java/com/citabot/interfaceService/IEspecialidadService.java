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
    public Especialidad update(Especialidad especialidad);
    public List<Especialidad> listarDisponiblesParaMedico(int medicoId);
    public List<Especialidad> listarRegistradasPorMedico(int medId);
    public List<Especialidad> listarDisponiblesPorDias(String dia);
}
