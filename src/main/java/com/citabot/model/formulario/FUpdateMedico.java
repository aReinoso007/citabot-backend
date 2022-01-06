package com.citabot.model.formulario;

import java.io.Serializable;

public class FUpdateMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    private String slogan;
    private String descripcion;
    private String numeroContacto;

    public FUpdateMedico() {
    }

    public FUpdateMedico(String slogan, String descripcion, String numeroContacto) {
        this.slogan = slogan;
        this.descripcion = descripcion;
        this.numeroContacto = numeroContacto;
    }


    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }
}
