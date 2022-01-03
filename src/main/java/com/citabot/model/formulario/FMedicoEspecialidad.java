package com.citabot.model.formulario;

import java.io.Serializable;

public class FMedicoEspecialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer medicoId;
    private Integer especialidadId;

    public FMedicoEspecialidad() {
    }

    public FMedicoEspecialidad(Integer medicoId, Integer especialidadId) {
        this.medicoId = medicoId;
        this.especialidadId = especialidadId;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Integer especialidadId) {
        this.especialidadId = especialidadId;
    }
}
