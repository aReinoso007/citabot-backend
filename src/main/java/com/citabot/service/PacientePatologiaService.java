package com.citabot.service;

import com.citabot.interfaceService.IPacientePatologiaService;
import com.citabot.interfaces.IPacientePatologia;
import com.citabot.model.PacientePatologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacientePatologiaService implements IPacientePatologiaService {

    @Autowired
    private IPacientePatologia data;


    @Override
    public List<PacientePatologia> listar() {
        return (List<PacientePatologia>) data.findAll();
    }

    @Override
    public Optional<PacientePatologia> listarByPacienteId(int id) {
        return data.listarByPacienteId(id);
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try {
            if(!data.existsById(id)){
                message = "RECORD DOES NOT EXIST";
            }else{
                data.deleteById(id);
            }

        }catch (Error error){
            System.out.printf("ERROR DELETING RECORD: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }
}
