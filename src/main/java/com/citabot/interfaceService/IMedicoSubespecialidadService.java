package com.citabot.interfaceService;

import com.citabot.model.MedicoSubespecialidad;

import java.util.List;
import java.util.Optional;

public interface IMedicoSubespecialidadService {

    public List<MedicoSubespecialidad> findAll();
    public Optional<MedicoSubespecialidad> findById(int id);
    public MedicoSubespecialidad save(int medico_id, int subespecialidad_id);
    public MedicoSubespecialidad edit(MedicoSubespecialidad medicoSubespecialidad);
    public void deleteRegistroSubespecialdiad(int id);
    public int getRegistroId(int medid, int subid);
}
