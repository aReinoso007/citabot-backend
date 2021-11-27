package com.citabot.interfaceService;

import com.citabot.model.Especialidad;
import com.citabot.model.MedicoEspecialidad;


import java.util.List;
import java.util.Optional;

public interface IMedicoEspecialidadService {


    public List<MedicoEspecialidad> findAll();
    public Optional<MedicoEspecialidad> findById(int id);
    public MedicoEspecialidad save(int medico_id, int especialidad_id);
    public MedicoEspecialidad edit(MedicoEspecialidad medicoEspecialidad);
    public String delete(int id);
}
