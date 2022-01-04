package com.citabot.model.formulario;

import java.io.Serializable;

public class FSubespecialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    private int medicoId;
    private int subespecialidadId;

    public FSubespecialidad() {
    }

    public FSubespecialidad(int medicoId, int subespecialidadId) {
        this.medicoId = medicoId;
        this.subespecialidadId = subespecialidadId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getSubespecialidadId() {
        return subespecialidadId;
    }

    public void setSubespecialidadId(int subespecialidadId) {
        this.subespecialidadId = subespecialidadId;
    }
}
