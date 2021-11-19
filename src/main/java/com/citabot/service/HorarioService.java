package com.citabot.service;

import com.citabot.interfaceService.IHorarioService;
import com.citabot.interfaces.IHorario;
import com.citabot.interfaces.IRegistroClinica;
import com.citabot.model.Horario;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HorarioService implements IHorarioService {
    @Autowired
    private IHorario data;
    @Autowired
    private IRegistroClinica dataRC;

    @Override
    public List<Horario> listar() {
        return (List<Horario>) data.findAll();
    }

    @Override
    public List<Horario> listarByClinicaRegistro(int id) {
        return (List<Horario>) data.findHorarioByRegistroClinica(id);
    }

    @Override
    public Horario save(Horario horario, int id) {
        Horario h = new Horario();
        RegistroClinica rc = new RegistroClinica();
        try{
            h = data.save(horario);
            rc = dataRC.findRegistroClinicaByRegistroClinicaId(id);
            h.setRegistroClinica(rc);
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
}
