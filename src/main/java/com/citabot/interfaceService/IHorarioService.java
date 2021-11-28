package com.citabot.interfaceService;

import com.citabot.model.Horario;
import com.citabot.model.RegistroClinica;

import java.util.List;
import java.util.Optional;

public interface IHorarioService {

    public List<Horario> listar();
    public List<Horario> listarByClinicaRegistro(int id);
    public Optional<Horario> listarById(int id);
    public Horario save(int idRegistro, Horario horario);
    public String delete(int id);


}
