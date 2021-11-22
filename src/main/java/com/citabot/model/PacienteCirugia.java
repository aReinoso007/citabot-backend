package com.citabot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PacienteCirugia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pacienteCirugiaId;

    private String tipo;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "cirugia_id")
    private Cirugia cirugia;

    public PacienteCirugia() {
    }

    public PacienteCirugia(Integer pacienteCirugiaId, String tipo, Paciente paciente, Cirugia cirugia) {
        this.pacienteCirugiaId = pacienteCirugiaId;
        this.tipo = tipo;
        this.paciente = paciente;
        this.cirugia = cirugia;
    }

    public Integer getPacienteCirugiaId() {
        return pacienteCirugiaId;
    }

    public void setPacienteCirugiaId(Integer pacienteCirugiaId) {
        this.pacienteCirugiaId = pacienteCirugiaId;
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

    public Cirugia getCirugia() {
        return cirugia;
    }

    public void setCirugia(Cirugia cirugia) {
        this.cirugia = cirugia;
    }
}
