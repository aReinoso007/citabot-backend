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
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
    private Paciente paciente;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
    private Cirugia cirugia;

    public PacienteCirugia() {
    }

    public PacienteCirugia(Paciente paciente, Cirugia cirugia) {
        this.paciente = paciente;
        this.cirugia = cirugia;
    }

    public Integer getPacienteCirugiaId() {
        return pacienteCirugiaId;
    }

    public void setPacienteCirugiaId(Integer pacienteCirugiaId) {
        this.pacienteCirugiaId = pacienteCirugiaId;
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
