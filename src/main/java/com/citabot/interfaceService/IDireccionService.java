package com.citabot.interfaceService;

import com.citabot.model.Direccion;

import java.util.List;

public interface IDireccionService {

    public List<Direccion> listar();
    public Direccion save(Direccion direccion);
    public Direccion update(Direccion direccion);
    public String delete(int id);

}
