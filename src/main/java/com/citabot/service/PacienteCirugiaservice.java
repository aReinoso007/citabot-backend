package com.citabot.service;

import com.citabot.interfaceService.IPacienteCirugiaService;

import com.citabot.interfaces.ICirugia;
import com.citabot.interfaces.IPaciente;
import com.citabot.interfaces.IPacienteCirugia;
import com.citabot.model.Cirugia;
import com.citabot.model.Paciente;
import com.citabot.model.PacienteCirugia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteCirugiaservice implements IPacienteCirugiaService {
    @Autowired
    private IPacienteCirugia data;
    @Autowired
    private IPaciente pacienteData;
    @Autowired
    private ICirugia cirugiaData;

    @Override
    @Transactional(readOnly = true)
    public List<PacienteCirugia> listar() {
        return (List<PacienteCirugia>) data.findAll();
    }

    @Override
    public Optional<PacienteCirugia> listarById(int id) {
        return data.findById(id);
    }

    @Override
    public PacienteCirugia save(int pacId, int cirId, String tipo) {

        Paciente p = new Paciente();
        Cirugia c = new Cirugia();

        PacienteCirugia pacienteCirugia = new PacienteCirugia();
        PacienteCirugia pCir = new PacienteCirugia();
        try{
            p = pacienteData.findById(pacId).get();
            pacienteCirugia.setPaciente(p);

            c = cirugiaData.findById(cirId).get();
            pacienteCirugia.setCirugia(c);
            pacienteCirugia.setTipo(tipo);

            pCir = data.save(pacienteCirugia);
        }catch (Error error){
            System.out.printf("ERROR SAVING RECORD: ", error.getMessage());
        }

        return pCir;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PacienteCirugia> listarByPacienteId(int id) {
        return Optional.empty();
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
