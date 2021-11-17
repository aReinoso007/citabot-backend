package com.citabot.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Medico extends Usuario{

    private String slogan;
    private String profesion;
    private String photoUrl;
    private String descripcion;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "medico" )
    private Set<RegistroClinica> clinicas;

    public Medico(String username, String nombre, String apellido, String email, String recoveryEmail, String password, String numeroContacto, Timestamp createdAt, Timestamp updatedAt, String slogan, String profesion, String photoUrl, String descripcion, Set<RegistroClinica> clinicas) {
        super(username, nombre, apellido, email, recoveryEmail, password, numeroContacto, createdAt, updatedAt);
        this.slogan = slogan;
        this.profesion = profesion;
        this.photoUrl = photoUrl;
        this.descripcion = descripcion;
        this.clinicas = clinicas;
    }

    public Medico(String slogan, String profesion, String photoUrl, String descripcion, Set<RegistroClinica> clinicas) {
        this.slogan = slogan;
        this.profesion = profesion;
        this.photoUrl = photoUrl;
        this.descripcion = descripcion;
        this.clinicas = clinicas;
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
}
