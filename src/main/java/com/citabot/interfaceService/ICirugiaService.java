package com.citabot.interfaceService;

import com.citabot.model.Cirugia;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ICirugiaService {
    public List<Cirugia> listar();
    public Cirugia save(Cirugia cirugia);
    public Cirugia update(Cirugia cirugia);
    public Boolean delete(int id);
    public Optional<Cirugia> findById(int id);
    public Optional<Cirugia> findByDate(Date date);
}
