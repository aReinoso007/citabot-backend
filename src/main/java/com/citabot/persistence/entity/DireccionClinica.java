package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DireccionClinica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer direccionClinicaId;
    @ManyToOne
    @JoinColumn
    private Clinica clinica;
    @ManyToOne
    @JoinColumn
    private Direccion direccion;

    public DireccionClinica() {
    }

    public DireccionClinica(Clinica clinica, Direccion direccion) {
        this.clinica = clinica;
        this.direccion = direccion;
    }

    public Integer getDireccionClinicaId() {
        return direccionClinicaId;
    }

    public void setDireccionClinicaId(Integer direccionClinicaId) {
        this.direccionClinicaId = direccionClinicaId;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
