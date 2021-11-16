package com.citabot.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Medico extends Usuario{

    private String slogan;
    private String profesion;
    private String photoUrl;
    private String descripcion;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "medico" )
    private Set<MedicoClinica> clinicas;


    public Medico(Integer usuarioId, String username, String name, String lastName, String email, String recoveryEmail, String password, String gender, String telephoneNumber, String cellphoneNumber, LocalDateTime createdAt, LocalDateTime updatedAt, String slogan, String profesion, String photoUrl, String descripcion, Set<MedicoClinica> clinicas) {
        super(usuarioId, username, name, lastName, email, recoveryEmail, password, gender, telephoneNumber, cellphoneNumber, createdAt, updatedAt);
        this.slogan = slogan;
        this.profesion = profesion;
        this.photoUrl = photoUrl;
        this.descripcion = descripcion;
        this.clinicas = clinicas;
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

    public Set<MedicoClinica> getClinicas() {
        return clinicas;
    }

    public void setClinicas(Set<MedicoClinica> clinicas) {
        this.clinicas = clinicas;
    }
}
