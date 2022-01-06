package com.citabot.service;


import com.citabot.interfaceService.IEspecialidadService;
import com.citabot.interfaceService.IMedicoEspecialidadService;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaces.IMedicoEspecialidad;


import com.citabot.model.Especialidad;
import com.citabot.model.Medico;
import com.citabot.model.MedicoEspecialidad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoEspecialidadService implements IMedicoEspecialidadService {

    @Autowired
    private IMedicoEspecialidad data;
    @Autowired
    private IMedicoService medData;
    @Autowired
    private IEspecialidadService espData;


    @Override
    public List<MedicoEspecialidad> findAll() {
        return (List<MedicoEspecialidad>) data.findAll();
    }

    @Override
    public Optional<MedicoEspecialidad> findById(int id) {
        return Optional.empty();
    }

    @Override
    public MedicoEspecialidad  save(int medico_id, int especialidad_id) {
        Medico m = new Medico();
        Especialidad especialidad = new Especialidad();


        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        MedicoEspecialidad medicoEspecialidad = new MedicoEspecialidad();
        MedicoEspecialidad mesp = new MedicoEspecialidad();
        try{
            m=medData.findById(medico_id).get();
            especialidad = espData.findById(especialidad_id).get();

            medicoEspecialidad.setMedico(m);
            medicoEspecialidad.setEspecialidadId(especialidad);
            medicoEspecialidad.setCreatedAt(ts);
            medicoEspecialidad.setUpdatedAt(ts);


            mesp = data.save(medicoEspecialidad);

        }catch(Error e){
            System.out.printf("Error registering Medico especialidad: ", e.getMessage());
        }

        return mesp;
    }

    @Override
    public MedicoEspecialidad edit(MedicoEspecialidad medicoEspecialidad) {
        return null;
    }


    @Override
    public void deleteRegistroEspecialidad(int id) {
        data.deleteById(id);
    }

    @Override
    public Integer getRegistroId(int medId, int espId) {
        return data.getRegistroId(medId, espId);
    }

}
