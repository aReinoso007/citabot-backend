package com.citabot.interfaceService;

import com.citabot.model.Enfermedad;

import java.util.List;

public interface IEnfermedadService {

    public List<Enfermedad> listar();
    public List<Enfermedad> listarByNombre(String nombre);
    public Enfermedad save(Enfermedad enfermedad);
    public Enfermedad edit(Enfermedad enfermedad);
    public String delete(int id);
}
