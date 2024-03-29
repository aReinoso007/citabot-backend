package com.citabot.interfaceService;

import com.citabot.model.Cita;
import com.citabot.model.formulario.FCitaDMedico;
import com.citabot.model.formulario.interfaces.CitaConstl;
import com.citabot.model.formulario.FCita;
import com.citabot.model.formulario.interfaces.CitaDets;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ICitaService {

    public List<Cita> listar();

    public Optional<Cita> listarById(int id);

    @Query(value = "SELECT * FROM CITA WHERE paciente_usuario_id=:id", nativeQuery = true)
    public List<Cita> listarByPacienteId(int id);

    public Cita save(FCita formularioCita, int paId, int regId);

    public String delete(int citaId);

    public Cita update(int citaId, String estado);

    public Optional<Cita> getCitasByPacienteIdAndEstado(int pId, String estado);

    public List<Cita> listarByRegistroId(int id);

    public List<String> citasOrdenadasFechaPorRegistro(int id);

    public List<String> citasOrdenadasFechaPorMedico(int id);

    public List<String> citasOrdenadasFechaPorClinica(int idClinica, int idMedico);

    public List<String> citasOrdenadasDiasPorRegistro();

    List<Cita> getHistorial(int id);
    List<CitaDets> getHistorialCitasDeMedico(int medId);
    List<Cita> getTodayCitas(long id);
    List<CitaDets> getCitasDeHoy(int medId);

    public List<CitaConstl> Listar_citas_paciente(int idPaciente);
    public List<CitaConstl> obtenerCitaDetalle(int idCita);


}
