package com.citabot.persistence;

import com.citabot.persistence.crud.CitaCRUDRepository;
import com.citabot.persistence.entity.Cita;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CitaRepository {

    private CitaCRUDRepository citaCRUDRepository;

    public List<Cita> getAll(){
        return (List<Cita>) citaCRUDRepository.findAll();
    }



    public Optional<List<Cita>> getCitasDoctorPorEstado(int idClinicaMedico, String estado){
        return citaCRUDRepository.findCitaByClinicaMedicoAndEstado(idClinicaMedico, estado);
    }
}
