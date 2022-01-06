package com.citabot.interfaceService;

import com.citabot.exceptions.EtAuthException;
import com.citabot.model.Medico;
import com.citabot.model.formulario.FMedico;

import java.util.List;
import java.util.Optional;

public interface IMedicoService {
    public List<Medico> listar();
    public List<Medico> listarByName(String name);
    public Optional<Medico> findById(long id);
    Medico validateMedico(String email, String password) throws EtAuthException;
    public Medico save(FMedico form);
    public Medico update(long id, Medico medico);
    public String delete(long id);
    public Medico buscarPorId(long id);
    public List<Medico> Listar_medicos_especialidad(int idEspecialidad);
    public List<Medico> listar_medicos_especialidadDia(int idEspecialidad, String dia);
    public List<Medico> Listar_medicos_clinica(int idclinica);
    public Medico findByEmail(String email);
    Medico findByEmailAndContrasena(String email, String password);
    Medico findByUsername(String username);
    boolean existeUsername(String username);
    Medico loginUsernamePassword(String username, String password);
}
