package com.citabot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class MedicoEspecialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer especialidadMedicoId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn
    private Medico medico;
    @ManyToOne
    @JoinColumn
    private Especialidad especialidad;

    public MedicoEspecialidad() {
    }

    public MedicoEspecialidad(Timestamp createdAt, Timestamp updatedAt, Medico medico, Especialidad especialidad) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.medico = medico;
        this.especialidad = especialidad;
    }

    public Integer getEspecialidadMedicoId() {
        return especialidadMedicoId;
    }

    public void setEspecialidadMedicoId(Integer especialidadMedicoId) {
        this.especialidadMedicoId = especialidadMedicoId;
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

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidadId(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
