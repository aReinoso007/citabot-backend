package com.citabot.interfaceService;

import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;

import java.util.List;

public interface ICitaService {

    public List<Cita> listar();
    public List<Cita> listarByPaciente(int pacienteId);
    public Cita save(Cita cita, Paciente paciente, RegistroClinica registroClinica);
    public String delete(int citaId, String estado);
}
