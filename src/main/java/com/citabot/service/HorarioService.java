package com.citabot.service;

import com.citabot.interfaceService.IHorarioService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.IHorario;
import com.citabot.model.Horario;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService implements IHorarioService {

    @Autowired
    private IHorario data;
    @Autowired
    private IRegistroClinicaService registroData;

    @Override
    public List<Horario> listar() {
        return (List<Horario>) data.findAll();
    }

    @Override
    public List<Horario> listarByClinicaRegistro(int id) {
        return data.buscarPorRegistro(id);
    }

    @Override
    public Optional<Horario> listarById(int id) {
        return data.findById(id);
    }

    @Override
    public Horario save(int idRegistro, Horario horario) {
        Horario h = new Horario();
        RegistroClinica registroClinica = new RegistroClinica();
        RegistroClinica rc = new RegistroClinica();
        try{
            registroClinica = registroData.findById(idRegistro).get();
            horario.setRegistroClinica(registroClinica);
            h = data.save(horario);
            return h;
        }catch (Error e){
            System.out.printf("Error saving: ", e.getMessage());
        }
        return h;
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try{
            data.deleteById(id);
        }catch (Error e){
            System.out.printf("Error deleting: ", e.getMessage());
            message = "FAILED";
        }
        return  message;
    }

    public Timestamp actualizado(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

}
