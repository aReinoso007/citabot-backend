package com.citabot.interfaceService;

import com.citabot.model.Cirugia;

import java.util.List;

public interface ICirugiaService {
    public List<Cirugia> listar();
    public Cirugia save(Cirugia cirugia);
    public Cirugia update(Cirugia cirugia);
    public String delete(int id);
}
