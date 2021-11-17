package com.citabot.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Medico extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String slogan;
    private String profesion;
    private String photoUrl;
    private String descripcion;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "medico" )
    private Set<RegistroClinica> clinicas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medico")
    private Set<MedicoEspecialidad> especialidades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medico")
    private Set<MedicoSubespecialidad> subespecialidades;


    public Medico(String username, String nombre, String apellido, String email, String recoveryEmail, String password, String numeroContacto, Timestamp createdAt, Timestamp updatedAt, String slogan, String profesion, String photoUrl, String descripcion) {
        super(username, nombre, apellido, email, recoveryEmail, password, numeroContacto, createdAt, updatedAt);
        this.slogan = slogan;
        this.profesion = profesion;
        this.photoUrl = photoUrl;
        this.descripcion = descripcion;
    }

    public Medico(String slogan, String profesion, String photoUrl, String descripcion) {
        this.slogan = slogan;
        this.profesion = profesion;
        this.photoUrl = photoUrl;
        this.descripcion = descripcion;
    }

    public Medico(Set<RegistroClinica> clinicas, Set<MedicoEspecialidad> especialidades, Set<MedicoSubespecialidad> subespecialidades) {
        this.clinicas = clinicas;
        this.especialidades = especialidades;
        this.subespecialidades = subespecialidades;
    }

    public Medico() {
        super();
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<RegistroClinica> getClinicas() {
        return clinicas;
    }

    public void setClinicas(Set<RegistroClinica> clinicas) {
        this.clinicas = clinicas;
    }

    public Set<MedicoEspecialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<MedicoEspecialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public Set<MedicoSubespecialidad> getSubespecialidades() {
        return subespecialidades;
    }

    public void setSubespecialidades(Set<MedicoSubespecialidad> subespecialidades) {
        this.subespecialidades = subespecialidades;
    }
}
