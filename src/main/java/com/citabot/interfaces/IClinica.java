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

    @Query(value = "select distinct on(clinica.clinica_id) * from horario, medico, clinica, registro_clinica\n" +
            "\twhere \n" +
            "\thorario.dia=:dia and\n" +
            "\thorario.registro_clinica_registro_clinica_id=registro_clinica.registro_clinica_id and\n" +
            "\tmedico.usuario_id = registro_clinica.medico_id and\n" +
            "    registro_clinica.clinica_id=clinica.clinica_id and \n" +
            "    medico.usuario_id=:medioId", nativeQuery = true)
    List<Clinica> listarClinicasPorDiaMedico(int medioId, String dia);


    /*Esta funcion me retorna las clinicas que el medico no tiene registradas */
    @Query(value = "select * from clinica\n" +
            "where NOT EXISTS(\n" +
            "\t\tSELECT clinica_id from registro_clinica\n" +
            "\t\twhere medico_id=:medicoId and\n" +
            "\t\tclinica_id = clinica.clinica_id)", nativeQuery = true)
    List<Clinica> listarClinicasDisponiblesParaMedico(int medicoId);
}
