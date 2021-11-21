package com.citabot.service;

import com.citabot.interfaceService.IDireccionPacienteService;
import com.citabot.interfaces.IDireccionPaciente;
import com.citabot.model.DireccionPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionPacienteService implements IDireccionPacienteService {

    @Autowired
    private IDireccionPaciente data;


    @Override
    public List<DireccionPaciente> listar() {
        return (List<DireccionPaciente>) data.findAll();
    }

    @Override
    public Optional<DireccionPaciente> listarByPacienteId(int id) {
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
