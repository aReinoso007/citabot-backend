package com.citabot.service;


import com.citabot.interfaceService.IEspecialidadService;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaceService.IMedicoSubespecialidadService;
import com.citabot.interfaceService.ISubespecialidadService;
import com.citabot.interfaces.IMedicoEspecialidad;
import com.citabot.interfaces.IMedicoSubespecialidad;
import com.citabot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoSubespecialidadService implements IMedicoSubespecialidadService {

    @Autowired
    private IMedicoSubespecialidad data;
    @Autowired
    private IMedicoService medData;
    @Autowired
    private ISubespecialidadService subespData;


    @Override
    public List<MedicoSubespecialidad> findAll() {
        return (List<MedicoSubespecialidad>) data.findAll();
    }

    @Override
    public Optional<MedicoSubespecialidad> findById(int id) {
        return Optional.empty();
    }

    @Override
    public MedicoSubespecialidad  save(int medico_id, int subespecialidad_id) {
        Medico m = new Medico();
        Subespecialidad subespecialidad = new Subespecialidad();

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        MedicoSubespecialidad medicosubEspecialidad = new MedicoSubespecialidad();
        MedicoSubespecialidad smesp = new MedicoSubespecialidad();
        try{
            m=medData.findById(medico_id).get();
            subespecialidad = subespData.findById(subespecialidad_id).get();

            medicosubEspecialidad.setMedico(m);
            medicosubEspecialidad.setSubespecialidad(subespecialidad);
            medicosubEspecialidad.setCreatedAt(ts);
            medicosubEspecialidad.setUpdatedAt(ts);


            smesp = data.save(medicosubEspecialidad);

        }catch(Error e){
            System.out.printf("Error registering Medico subespecialidad: ", e.getMessage());
        }

        return smesp;
    }

    @Override
    public MedicoSubespecialidad edit(MedicoSubespecialidad medicoSubespecialidad) {
        return null;
    }

    @Override
    public void deleteRegistroSubespecialdiad(int id) {
        data.deleteById(id);
    }

    @Override
    public int getRegistroId(int medid, int subid) {
        return data.getRegistroId(medid, subid);
    }

}
