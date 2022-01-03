package com.citabot.model.formulario;

import java.io.Serializable;

public class FCirugia implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idPaciente;
    private Integer idCirugia;
    private String tipo;

    public FCirugia(Integer idPaciente, Integer idCirugia, String tipo) {
        this.idPaciente = idPaciente;
        this.idCirugia = idCirugia;
        this.tipo = tipo;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdCirugia() {
        return idCirugia;
    }

    public void setIdCirugia(Integer idCirugia) {
        this.idCirugia = idCirugia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
