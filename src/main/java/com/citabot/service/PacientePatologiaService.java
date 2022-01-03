package com.citabot.service;

import com.citabot.interfaceService.IPacientePatologiaService;
import com.citabot.interfaces.IEnfermedad;
import com.citabot.interfaces.IPaciente;
import com.citabot.interfaces.IPacientePatologia;
import com.citabot.model.Enfermedad;
import com.citabot.model.Paciente;
import com.citabot.model.PacientePatologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacientePatologiaService implements IPacientePatologiaService {

    @Autowired
    private IPacientePatologia data;
    @Autowired
    private IEnfermedad enfermedadData;
    @Autowired
    private IPaciente pacienteData;


    @Override
    public List<PacientePatologia> listar() {
        return (List<PacientePatologia>) data.findAll();
    }

    @Override
    public List<PacientePatologia> listarByPacienteId(long id) {
        return data.listarByPacienteId(id);
    }

    @Override
    public Optional<PacientePatologia> listarById(int id) {
        return data.findById(id);
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

    @Override
    public PacientePatologia save(String tipo, long pacienteId, int enfermedadId) {
        Paciente paciente = new Paciente();
        Enfermedad enfermedad = new Enfermedad();
        PacientePatologia pacientePatologia = new PacientePatologia();
        PacientePatologia pacPat = new PacientePatologia();
        try{
            if(enfermedadData.existsById(enfermedadId) && pacienteData.existsById(pacienteId)){
                enfermedad = enfermedadData.findById(enfermedadId).get();
                pacientePatologia.setEnfermedad(enfermedad);

                paciente = pacienteData.findById(pacienteId).get();
                pacientePatologia.setPaciente(paciente);

                pacientePatologia.setTipo(tipo);

                pacPat = data.save(pacientePatologia);
                return pacPat;
            }else{
                return null;
            }
        }catch (Error error){
            System.out.printf("Error saving patologia: ", error.getMessage());
        }
        return pacPat;
    }

   // @Override
//    public PacientePatologia update(PacientePatologia pacientePatologia){return data.save(pacientePatologia);}
}
