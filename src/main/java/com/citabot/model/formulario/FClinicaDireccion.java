package com.citabot.model.formulario;

import java.io.Serializable;

public class FClinicaDireccion implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer clinicaId;
    private Integer direccionId;

    public FClinicaDireccion() {
    }

    public FClinicaDireccion(Integer clinicaId, Integer direccionId) {
        this.clinicaId = clinicaId;
        this.direccionId = direccionId;
    }

    public Integer getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(Integer clinicaId) {
        this.clinicaId = clinicaId;
    }

    public Integer getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Integer direccionId) {
        this.direccionId = direccionId;
    }
}
