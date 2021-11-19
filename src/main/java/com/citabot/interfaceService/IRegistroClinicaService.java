package com.citabot.interfaceService;

import com.citabot.model.Clinica;
import com.citabot.model.Medico;
import com.citabot.model.RegistroClinica;

public interface IRegistroClinicaService {

    public RegistroClinica save(Clinica clinica, Medico medico);
    public RegistroClinica edit(RegistroClinica registroClinica);
    public String delete(int id);
}
