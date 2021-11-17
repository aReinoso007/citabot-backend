package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreClinica;
    private String contacto;
    private String photoUrl;
    private String genero;
    @OneToMany(mappedBy = "clinica")
    private Set<DireccionClinica> direccionClinicas;

    public Clinica() {
    }

    public Clinica(Integer id, String nombreClinica, String contacto, String photoUrl, Set<DireccionClinica> direccionClinicas) {
        this.id = id;
        this.nombreClinica = nombreClinica;
        this.contacto = contacto;
        this.photoUrl = photoUrl;
        this.direccionClinicas = direccionClinicas;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreClinica() {
        return nombreClinica;
    }

    public void setNombreClinica(String nombreClinica) {
        this.nombreClinica = nombreClinica;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Set<DireccionClinica> getDireccionClinicas() {
        return direccionClinicas;
    }

    public void setDireccionClinicas(Set<DireccionClinica> direccionClinicas) {
        this.direccionClinicas = direccionClinicas;
    }
}
