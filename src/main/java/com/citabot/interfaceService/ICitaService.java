package com.citabot.interfaceService;

import com.citabot.model.Cita;
import com.citabot.model.Paciente;
import com.citabot.model.RegistroClinica;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICitaService {

    public List<Cita> listar();
    public List<Cita> listarById(int id);
    public List<Cita> listarByPacienteId(int id);
    @Query(value = "SELECT * FROM CITA WHERE paciente_usuario_id=?", nativeQuery = true)
    public Cita save(Cita cita);
    public String delete(int citaId, String estado);
}
