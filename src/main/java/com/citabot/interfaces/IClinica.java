package com.citabot.interfaces;

import com.citabot.model.Clinica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClinica extends CrudRepository<Clinica, Integer> {

    List<Clinica> findClinicaByNombreClinica(String nombre);
    Clinica findClinicaByClinicaId(int clinicaId);

    @Query(value = "select * from medico, clinica, registro_clinica\n" +
            "where medico.usuario_id = registro_clinica.medico_id and\n" +
            "registro_clinica.clinica_id=clinica.clinica_id and \n" +
            "medico.usuario_id=?1", nativeQuery = true)
    List<Clinica> listarClinicasPorMedico(int medioId);
}
