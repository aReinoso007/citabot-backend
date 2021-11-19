package com.citabot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer especialidadId;
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especialidad")
    private List<Subespecialidad> subespecialidades;

    public Especialidad() {
    }

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }

    public Especialidad(String nombre, List<Subespecialidad> subespecialidades) {
        this.nombre = nombre;
        this.subespecialidades = subespecialidades;
    }

    public Integer getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Integer especialidadId) {
        this.especialidadId = especialidadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Subespecialidad> getSubespecialidades() {
        return subespecialidades;
    }

    public void setSubespecialidades(List<Subespecialidad> subespecialidades) {
        this.subespecialidades = subespecialidades;
    }
}
