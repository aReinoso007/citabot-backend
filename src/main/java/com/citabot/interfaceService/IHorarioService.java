package com.citabot.interfaceService;

import com.citabot.model.Horario;
import com.citabot.model.RegistroClinica;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IHorarioService {

    public List<Horario> listar();
    public List<Horario> listarByClinicaRegistro(int id);
    public Optional<Horario> listarById(int id);
    public Horario save(int idRegistro, Horario horario);
    public void deleteHorario(int id);

    public List<String> listarDiasDelHorarioPorRegistro(int id);

    /*Devuelve fechas disponibles y hace uso de la funcion
    * horariosRegistroOrdenado para agregar los intervalos de tiempo
    * a las fechas disponibles */
    public List<LocalDateTime> listarFechasDisponibles(int id);

    /*Me lista los dias e intervalos de tiempo, esta es importantes */
    List<String> horariosRegistroOrdenado(int id);

    List<LocalDateTime> listarDiasDisponibles();

    /*Esto es para poder mostrar de mejor manera en el front */
    List<Horario> horarioOrdenadoObj(int id);
}
