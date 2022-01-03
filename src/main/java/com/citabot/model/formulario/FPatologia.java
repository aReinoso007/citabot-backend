package com.citabot.model.formulario;

import java.io.Serializable;

public class FPatologia implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idPaciente;
    private Integer idEnfermedad;
    private String tipo;

    public FPatologia(Integer idPaciente, Integer idEnfermedad, String tipo) {
        this.idPaciente = idPaciente;
        this.idEnfermedad = idEnfermedad;
        this.tipo = tipo;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(Integer idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
