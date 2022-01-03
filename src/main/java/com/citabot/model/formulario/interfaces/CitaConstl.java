package com.citabot.model.formulario.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

public interface CitaConstl {
     Integer getcitaId();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.s", timezone = "America/Guayaquil")
     Timestamp getfechaCita();
     String getsintomas();
     Integer getclinicaId();
     Integer getmedicoId();

}
