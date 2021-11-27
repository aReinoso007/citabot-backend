package com.citabot.service;

import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaces.IMedico;
import com.citabot.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService implements IMedicoService {

    @Autowired
    private IMedico data;

    @Override
    @Transactional(readOnly = true)
    public List<Medico> listar() {
        return (List<Medico>) data.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> listarByName(String name) {
        return (List<Medico>) data.findMedicoByNombre(name);
    }

    @Override
    @Transactional(readOnly = true)
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
    public List<Medico> buscarCedula(int idEspecialidad) {
        System.out.println("Consulta Realizada...");
        Query nativeQuery = em.createNativeQuery(
                "SELECT id, numero, operadora, tipo, usuario_cedula FROM usuario, telefono WHERE telefono.usuario_cedula=usuario.cedula and usuario.cedula= ?",
                Telefono.class);
        nativeQuery.setParameter(1, cedula);
        System.out.println("Consulta Realizada...");
        return (List<Telefono>) nativeQuery.getResultList();
    }




    @Override
    @Transactional(readOnly = true)
    public Medico buscarPorId(int id) {
        return (Medico) data.findMedicoByUsuarioId(id);
    }
}
