package com.citabot.persistence.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subespecialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subespecialidadId;
    private String nombre;

    public Subespecialidad() {
    }

    public Subespecialidad(Integer subespecialidadId, String nombre) {
        this.subespecialidadId = subespecialidadId;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Subespecialidad{" +
                "subespecialidadId=" + subespecialidadId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
