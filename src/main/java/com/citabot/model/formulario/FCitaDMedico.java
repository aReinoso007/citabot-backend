package com.citabot.model.formulario;

import java.io.Serializable;

public class FCitaDMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer citaId;
    private String fechaCita;
    private String sintomas;
    private String estado;
    private String clinica;
    private String paciente;

    public FCitaDMedico() {
    }

    public FCitaDMedico(Integer citaId, String fechaCita, String sintomas, String estado, String clinica, String paciente) {
        this.citaId = citaId;
        this.fechaCita = fechaCita;
        this.sintomas = sintomas;
        this.estado = estado;
        this.clinica = clinica;
        this.paciente = paciente;
    }

    public Integer getCitaId() {
        return citaId;
    }

    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
}
