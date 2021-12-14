package com.citabot.interfaceService;

import com.citabot.exceptions.EtAuthException;
import com.citabot.model.Medico;

import java.util.List;
import java.util.Optional;

public interface IMedicoService {
    public List<Medico> listar();
    public List<Medico> listarByName(String name);
    public Optional<Medico> findById(long id);
    Medico validateMedico(String email, String password) throws EtAuthException;
    public Medico save(Medico medico);
    public Medico update(long id, Medico medico);
    public String delete(long id);
    public Medico buscarPorId(long id);
    public List<Medico> Listar_medicos_especialidad(int idEspecialidad);
    public Medico findByEmail(String email);
    Medico findByEmailAndContrasena(String email, String password);
    Medico findByUsername(String username);
    boolean existeUsername(String username);
    Medico loginUsernamePassword(String username, String password);
}
