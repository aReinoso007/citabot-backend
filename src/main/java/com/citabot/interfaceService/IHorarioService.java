package com.citabot.interfaceService;

import com.citabot.model.Horario;
import com.citabot.model.RegistroClinica;

import java.util.List;

public interface IHorarioService {

    public List<Horario> listar();
    public List<Horario> listarByClinicaRegistro(int id);
    public Horario save(Horario horario, RegistroClinica registroClinica);
    public String delete(int id);


}
