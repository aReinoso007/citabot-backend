package com.citabot.service;

import com.citabot.interfaceService.IHorarioService;
import com.citabot.interfaces.IHorario;
import com.citabot.interfaces.IRegistroClinica;
import com.citabot.model.Horario;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public Optional<Horario> listarById(int id) {
        return data.findById(id);
    }

    @Override
    public Horario save(Horario horario, int idRegistroClinica) {
        Horario h = new Horario();
        RegistroClinica registroClinica = new RegistroClinica();
        RegistroClinica rc = new RegistroClinica();
        try{
            /* Obtengo el registro de clincica para insertar */
            registroClinica = dataRC.findById(idRegistroClinica).get();
            /*le asigno al horario que se esta pasando */
            horario.setRegistroClinica(registroClinica);
            /*Se guarda en la db el horario */
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
}
