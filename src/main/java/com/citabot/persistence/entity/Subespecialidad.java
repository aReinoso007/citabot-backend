package com.citabot.persistence.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Subespecialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subespecialidadId;
    private String nombre;
    @ManyToOne
    @JoinColumn
    private Especialidad especialidad;

    public Subespecialidad() {
    }

    public Subespecialidad(String nombre, Especialidad especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public Integer getSubespecialidadId() {
        return subespecialidadId;
    }

    public void setSubespecialidadId(Integer subespecialidadId) {
        this.subespecialidadId = subespecialidadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
