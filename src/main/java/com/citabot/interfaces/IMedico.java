package com.citabot.interfaces;

import com.citabot.model.Medico;
import com.citabot.model.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IMedico extends CrudRepository<Medico, Integer> {

    List<Medico> findMedicoByNombre(String nombre);
    @Query(value = "SELECT * FROM medico WHERE usuario_id= :usuarioId", nativeQuery = true)
    Medico findMedicoByUsuarioId(int usuarioId);
    Optional<Medico> findMedicoByEmail(String email);
    @Query(value = "SELECT * FROM medico WHERE medico.email=?1 AND medico.password=?2", nativeQuery = true)
    Optional<Medico> login(String email, String password);
    Medico findMedicoByEmailAndPassword(String email, String password);


}
