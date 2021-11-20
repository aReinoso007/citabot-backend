package com.citabot.service;

import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaces.IMedico;
import com.citabot.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService implements IMedicoService {

    @Autowired
    private IMedico data;

    @Override
    public List<Medico> listar() {
        return (List<Medico>) data.findAll();
    }

    @Override
    public List<Medico> listarByName(String name) {
        return (List<Medico>) data.findMedicoByNombre(name);
    }

    @Override
    public Optional<Medico> findById(int id) {
        return data.findById(id);
    }

    @Override
    public Medico save(Medico medico) {
        /*Add encryption */
        return data.save(medico);
    }

    @Override
    public Medico edit(Medico medico) {
        return data.save(medico);
    }

    @Override
    public String delete(int id) {
        String message = "SUCCESS";
        try{
            data.deleteById(id);
        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }

    @Override
    public Medico buscarPorId(int id) {
        return (Medico) data.findMedicoByUsuarioId(id);
    }
}
