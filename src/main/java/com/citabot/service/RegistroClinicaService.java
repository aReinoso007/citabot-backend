package com.citabot.service;

import com.citabot.interfaceService.IClinicaService;
import com.citabot.interfaceService.IMedicoService;
import com.citabot.interfaceService.IRegistroClinicaService;
import com.citabot.interfaces.IRegistroClinica;
import com.citabot.model.Clinica;
import com.citabot.model.Medico;
import com.citabot.model.RegistroClinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroClinicaService implements IRegistroClinicaService {
    @Autowired
    private IRegistroClinica data;
    @Autowired
    private IMedicoService medData;
    @Autowired
    private IClinicaService cliData;


    @Override
    public List<RegistroClinica> findAll() {
        return (List<RegistroClinica>) data.findAll();
    }

    @Override
    public Optional<RegistroClinica> findById(int id) {
        return data.findById(id);
    }

    @Override
    public RegistroClinica save(int idClinica, int idMedico) {
        Medico m = new Medico();
        Clinica c = new Clinica();
        RegistroClinica registroClinica = new RegistroClinica();
        RegistroClinica regc = new RegistroClinica();
        try{
            c = cliData.findById(1).get();
            System.out.printf(" Clinic retrieved: " + c.getNombreClinica());
            registroClinica.setClinica(c);

            m = medData.findById(1).get();
            System.out.printf(" Medic retrieved: "+ m);
            registroClinica.setMedico(m);

            regc = data.save(registroClinica);

        }catch(Error e){
            System.out.printf("Error registering clinic: ", e.getMessage());
        }

        return regc;
    }

    @Override
    public RegistroClinica edit(RegistroClinica registroClinica) {
        return data.save(registroClinica);
    }

    @Override
    public String delete(int id) {
        return null;
    }

    @Override
    public Optional<RegistroClinica> findByMedico(int id) {
        return data.findRegistroClinicasByMedico(id);
    }

    @Override
    public Optional<RegistroClinica> finByClinicaAndMedico(int cliId, int medId) {
        return data.getRegistroClinicaByClinicaAndMedico(cliId, medId);
    }

    /*
    @Override
    public RegistroClinica save(RegistroClinica registroClinica, int clinicaId, int medicoId) {
        Medico m = new Medico();
        Clinica c = new Clinica();
        RegistroClinica regc = new RegistroClinica();
        try{
            m = medData.findById(medicoId).get();
            System.out.printf("Medic retrieved:", m);
            c = cliData.findById(clinicaId).get();
            System.out.printf("Clinic retrieved:", c);
            registroClinica.setClinica(c);
            registroClinica.setMedico(m);
            regc = data.save(registroClinica);
        }catch(Exception e){
            System.out.printf("Error registering clinic: ", e.getMessage());
        }

        return regc;
    }
     */

}
