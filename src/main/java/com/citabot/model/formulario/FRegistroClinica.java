package com.citabot.model.formulario;

import java.io.Serializable;

public class FRegistroClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer medicoId;
    private Integer clinicaId;

    public FRegistroClinica() {
    }

    public FRegistroClinica(Integer medicoId, Integer clinicaId) {
        this.medicoId = medicoId;
        this.clinicaId = clinicaId;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(Integer clinicaId) {
        this.clinicaId = clinicaId;
    }
}
