package com.citabot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class PacientePatologia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pacientePatologiaId;
    private String tipo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
    private Paciente paciente;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "enfermedad_id", insertable = false, updatable = false)
    private Enfermedad enfermedad;

    public PacientePatologia() {
    }

    public PacientePatologia(String tipo, Paciente paciente, Enfermedad enfermedad) {
        this.tipo = tipo;
        this.paciente = paciente;
        this.enfermedad = enfermedad;
    }

    public Integer getPacientePatologiaId() {
        return pacientePatologiaId;
    }

    public void setPacientePatologiaId(Integer pacientePatologiaId) {
        this.pacientePatologiaId = pacientePatologiaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }
}
