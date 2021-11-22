package com.citabot.service;


import com.citabot.interfaceService.IDireccionClinicaService;
import com.citabot.interfaces.IClinica;
import com.citabot.interfaces.IDireccion;
import com.citabot.interfaces.IDireccionClinica;
import com.citabot.model.Clinica;
import com.citabot.model.Direccion;
import com.citabot.model.DireccionClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionClinicaService implements IDireccionClinicaService {

    @Autowired
    private IDireccionClinica data;
    @Autowired
    private IClinica clinicaData;
    @Autowired
    private IDireccion direccionData;

    @Override
    public List<DireccionClinica> listar() {
        return (List<DireccionClinica>) data.findAll();
    }

    @Override
    public DireccionClinica save(int clinica_id, int direccion_id) {
        DireccionClinica direccionClinica = new DireccionClinica();
        Clinica clinica = new Clinica();
        Direccion direccion = new Direccion();
        DireccionClinica rs = new DireccionClinica();
        try{
            if(clinicaData.existsById(clinica_id)&direccionData.existsById(direccion_id)){
                clinica = clinicaData.findById(clinica_id).get();
                direccion = direccionData.findById(direccion_id).get();
                direccionClinica.setClinica(clinica);
                direccionClinica.setDireccion(direccion);
                rs = data.save(direccionClinica);
            }
        }catch (Error error){
            System.out.printf("ERROR SAVING RECORD: ", error.getMessage());
        }
        return rs;
    }

    @Override
    public DireccionClinica update(DireccionClinica direccionClinica) {
        Direccion direccion = direccionData.findById(direccionClinica.getDireccion().getDireccionId()).get();
        Clinica clinica = clinicaData.findById(direccionClinica.getClinica().getClinicaId()).get();
        DireccionClinica diC = new DireccionClinica();
        try{
            if(direccionData.existsById(direccion.getDireccionId()) && clinicaData.existsById(clinica.getClinicaId()) ){
                diC = data.save(direccionClinica);
            }
        }catch (Error error){
            System.out.printf("ERROR UPDATING RECORD: ", error.getMessage());
        }


        return diC;
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
    /*Retorna el registro por el ID de la clinica */
    @Override
    public Optional<DireccionClinica> listarByClinicaId(int cliId) {
        return data.listarDireccionesByClinicaId(cliId);
    }
}
