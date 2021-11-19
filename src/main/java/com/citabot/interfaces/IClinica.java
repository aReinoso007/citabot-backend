package com.citabot.interfaces;

import com.citabot.model.Clinica;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClinica extends CrudRepository<Clinica, Integer> {

    List<Clinica> findClinicaByNombreClinica(String nombre);
}
