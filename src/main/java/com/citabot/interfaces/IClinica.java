package com.citabot.interfaces;

import com.citabot.model.Clinica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClinica extends CrudRepository<Clinica, Integer> {

    List<Clinica> findClinicaByNombreClinica(String nombre);
}
