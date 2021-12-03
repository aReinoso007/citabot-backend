package com.citabot.model.formulario;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FCita implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime fechaCita;
    private String sintomas;

    public FCita() {
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }
}
