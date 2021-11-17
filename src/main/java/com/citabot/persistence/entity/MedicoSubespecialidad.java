package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class MedicoSubespecialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
}
