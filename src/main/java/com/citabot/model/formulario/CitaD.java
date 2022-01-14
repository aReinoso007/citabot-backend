package com.citabot.model.formulario;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

public class CitaD implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer citaId;
 /*   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.s", timezone = "America/Guayaquil")
    private Timestamp fechaCita;*/
    private String fechaCita;
    private String sintomas;
    private String clinica;
    private String medico;
    private String especialidad;

    public CitaD() {
    }

    public CitaD(Integer citaId, String fechaCita, String sintomas, String clinica, String medico, String especialidad) {
        this.citaId = citaId;
        this.fechaCita = fechaCita;
        this.sintomas = sintomas;
        this.clinica = clinica;
        this.medico = medico;
        this.especialidad = especialidad;
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

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
