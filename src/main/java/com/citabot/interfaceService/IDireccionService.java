package com.citabot.interfaceService;

import com.citabot.model.Direccion;

import java.util.List;
import java.util.Optional;

public interface IDireccionService {

    public List<Direccion> listar();
    public Direccion save(Direccion direccion);
    public Direccion update(Direccion direccion);
    public Optional<Direccion> findById(int id);
    public String delete(int id);

}
