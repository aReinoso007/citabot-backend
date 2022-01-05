package com.citabot.service;

import com.citabot.interfaceService.IDireccionPacienteService;
import com.citabot.interfaces.IDireccion;
import com.citabot.interfaces.IDireccionPaciente;
import com.citabot.interfaces.IPaciente;
import com.citabot.model.Direccion;
import com.citabot.model.DireccionPaciente;
import com.citabot.model.Paciente;
import com.citabot.model.formulario.FPDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionPacienteService implements IDireccionPacienteService {

    @Autowired
    private IDireccionPaciente data;
    @Autowired
    private IPaciente pacienteData;
    @Autowired
    private IDireccion direccionData;


    @Override
    public List<DireccionPaciente> listar() {
        return (List<DireccionPaciente>) data.findAll();
    }

    @Override
    public Optional<DireccionPaciente> listarByPacienteId(int id) {
        return data.listarByPacienteId(id);
    }

    @Override
    public Optional<DireccionPaciente> listarById(int id) {
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
    public DireccionPaciente save(int direccionId, long pacienteId, String tipo) {
        Paciente paciente = new Paciente();
        Direccion direccion = new Direccion();
        DireccionPaciente direccionPaciente = new DireccionPaciente();
        DireccionPaciente dirPaciente = new DireccionPaciente();
        try{

            paciente = pacienteData.findById(pacienteId).get();
            System.out.printf("Paciente recuperado: ", paciente.toString());
            direccion = direccionData.findById(direccionId).get();
            System.out.printf("Direccion recuperada: ", direccion.toString());

            direccionPaciente.setDireccion(direccion);
            direccionPaciente.setPaciente(paciente);
            direccionPaciente.setTipo(tipo);
            dirPaciente = data.save(direccionPaciente);
            if(dirPaciente==null){
                return null;
            }

        }catch (Error error){
            System.out.printf("ERROR ADDINT PATIENT ADDRESS: ", error.getMessage());
        }


        return dirPaciente;
    }

    @Override
    public DireccionPaciente update(FPDireccion formulario) {

        DireccionPaciente direccionPaciente = new DireccionPaciente();
        Paciente paciente = new Paciente();
        Direccion direccion = new Direccion();

        paciente = pacienteData.findPacienteByUsuarioId(formulario.getIdPaciente()).get();
        direccion = direccionData.findById(formulario.getIdDireccion()).get();

        direccionPaciente.setDireccionPacienteId(formulario.getIdPacienteDireccion());
        direccionPaciente.setTipo(formulario.getTipo());
        direccionPaciente.setPaciente(paciente);
        direccionPaciente.setDireccion(direccion);


        return data.save(direccionPaciente);
    }
}
