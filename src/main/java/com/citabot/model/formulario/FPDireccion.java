package com.citabot.model.formulario;

import java.io.Serializable;

public class FPDireccion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idPaciente;
    private Integer idDireccion;
    private String tipo;

    public FPDireccion(Integer idPaciente, Integer idDireccion, String tipo) {
        this.idPaciente = idPaciente;
        this.idDireccion = idDireccion;
        this.tipo = tipo;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
