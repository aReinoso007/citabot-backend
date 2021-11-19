package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class MedicoSubespecialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicoSubespecialidadId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn
    private Medico medico;
    @ManyToOne
    @JoinColumn
    private Subespecialidad subespecialidad;

    public MedicoSubespecialidad() {
    }

    public MedicoSubespecialidad(Timestamp createdAt, Timestamp updatedAt, Medico medico, Subespecialidad subespecialidad) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.medico = medico;
        this.subespecialidad = subespecialidad;
    }

    public Integer getMedicoSubespecialidadId() {
        return medicoSubespecialidadId;
    }

    public void setMedicoSubespecialidadId(Integer medicoSubespecialidadId) {
        this.medicoSubespecialidadId = medicoSubespecialidadId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Subespecialidad getSubespecialidad() {
        return subespecialidad;
    }

    public void setSubespecialidad(Subespecialidad subespecialidad) {
        this.subespecialidad = subespecialidad;
    }
}
