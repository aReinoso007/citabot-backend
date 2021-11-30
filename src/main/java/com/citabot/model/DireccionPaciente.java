package com.citabot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class DireccionPaciente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer direccionPacienteId;
    private String tipo;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    public DireccionPaciente() {
    }

    public DireccionPaciente(String tipo, Paciente paciente, Direccion direccion) {
        this.tipo = tipo;
        this.paciente = paciente;
        this.direccion = direccion;
    }

    public Integer getDireccionPacienteId() {
        return direccionPacienteId;
    }

    public void setDireccionPacienteId(Integer direccionPacienteId) {
        this.direccionPacienteId = direccionPacienteId;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
