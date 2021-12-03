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
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
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
    @Transactional(readOnly = true)
    public Optional<RegistroClinica> findById(int id) {
        return data.findById(id);
    }

    @Override
    public RegistroClinica save(int idClinica, int idMedico) {
        Medico m = new Medico();
        Clinica c = new Clinica();
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        RegistroClinica registroClinica = new RegistroClinica();
        RegistroClinica regc = new RegistroClinica();
        try{
            c = cliData.findById(idClinica).get();
            registroClinica.setClinica(c);
            m = medData.findById(idMedico).get();
            registroClinica.setMedico(m);
            registroClinica.setCreatedAt(ts);
            registroClinica.setUpdatedAt(ts);

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
        String message = "SUCCESS";
        try {
            if(!data.existsById(id)){
                message = "Registro no existe";
            }else{
                data.deleteById(id);
            }

        }catch (Error error){
            System.out.printf("Error deleting: ", error.getMessage());
            message = "FAILED";
        }
        return message;
    }

    @Override
    public Optional<RegistroClinica> findByMedico(int id) {
        return data.findRegistroClinicasByMedico(id);
    }

    @Override
    public int finByClinicaAndMedico(int cliId, int medId) {
        return data.getRegistroClinicaByClinicaAndMedico(cliId, medId);
    }

}
