package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class PacientePatologia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    @ManyToOne
    @JoinColumn
    private Paciente paciente;
    @ManyToOne
    @JoinColumn
    private Enfermedad enfermedad;

    public PacientePatologia() {
    }

    public PacientePatologia(String tipo, Paciente paciente, Enfermedad enfermedad) {
        this.tipo = tipo;
        this.paciente = paciente;
        this.enfermedad = enfermedad;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
