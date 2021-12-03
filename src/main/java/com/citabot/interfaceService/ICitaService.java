package com.citabot.interfaceService;

import com.citabot.model.Cita;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICitaService {

    public List<Cita> listar();
    public Optional<Cita> listarById(int id);
    @Query(value = "SELECT * FROM CITA WHERE paciente_usuario_id=:id", nativeQuery = true)
    public List<Cita> listarByPacienteId(int id);

    public Cita save(Cita cita, int paId, int regId);
    public String delete(int citaId);
    public Cita update(int citaId, String estado);
    public Optional<Cita> getCitasByPacienteIdAndEstado(int pId, String estado);
    public List<Cita> listarByRegistroId(int id);
    public List<String> citasOrdenadasFechaPorRegistro(int id);
}
