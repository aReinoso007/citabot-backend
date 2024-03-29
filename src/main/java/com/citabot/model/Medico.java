package com.citabot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class Medico extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String slogan;
    private String profesion;
    private String photoUrl;
    private String descripcion;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "medico" )
    private List<RegistroClinica> clinicas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medico")
    private List<MedicoEspecialidad> especialidades = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medico")
    private List<MedicoSubespecialidad> subespecialidades = new ArrayList<>();
    private String role;

    public Medico(String slogan, String profesion, String photoUrl, String descripcion, List<RegistroClinica> clinicas, List<MedicoEspecialidad> especialidades, List<MedicoSubespecialidad> subespecialidades, String role) {
        this.slogan = slogan;
        this.profesion = profesion;
        this.photoUrl = photoUrl;
        this.descripcion = descripcion;
        this.clinicas = clinicas;
        this.especialidades = especialidades;
        this.subespecialidades = subespecialidades;
        this.role = role;
    }

    public Medico(String username, String estado, String nombre, String apellido, String email, String recoveryEmail, String password, String numeroContacto, Timestamp createdAt, Timestamp updatedAt, String slogan, String profesion, String photoUrl, String descripcion, List<RegistroClinica> clinicas, List<MedicoEspecialidad> especialidades, List<MedicoSubespecialidad> subespecialidades, String role) {
        super(username, estado, nombre, apellido, email, recoveryEmail, password, numeroContacto, createdAt, updatedAt);
        this.slogan = slogan;
        this.profesion = profesion;
        this.photoUrl = photoUrl;
        this.descripcion = descripcion;
        this.clinicas = clinicas;
        this.especialidades = especialidades;
        this.subespecialidades = subespecialidades;
        this.role = role;
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

    public List<RegistroClinica> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<RegistroClinica> clinicas) {
        this.clinicas = clinicas;
    }

    public List<MedicoEspecialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<MedicoEspecialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<MedicoSubespecialidad> getSubespecialidades() {
        return subespecialidades;
    }

    public void setSubespecialidades(List<MedicoSubespecialidad> subespecialidades) {
        this.subespecialidades = subespecialidades;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
