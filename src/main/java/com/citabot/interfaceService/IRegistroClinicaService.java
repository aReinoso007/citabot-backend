package com.citabot.interfaceService;

import com.citabot.model.RegistroClinica;

import java.util.List;
import java.util.Optional;

public interface IRegistroClinicaService {
    public List<RegistroClinica> findAll();
    public Optional<RegistroClinica> findById(int id);
    public RegistroClinica save(int idCLinica, int idMedico);
    public RegistroClinica edit(RegistroClinica registroClinica);
    public String delete(int id);
    public Optional<RegistroClinica> findByMedico(int id);
    public int finByClinicaAndMedico(int cliId, int medId);
}
