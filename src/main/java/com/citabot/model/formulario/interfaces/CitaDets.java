package com.citabot.model.formulario.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public interface CitaDets {
    Integer getCitaId();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.s", timezone = "America/Guayaquil")
    Timestamp getfechaCita();
    String getsintomas();
    String getEstado();
    Integer getclinicaId();
    Integer getpacienteId();
}
