package com.citabot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

@Entity
public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clinicaId;
    private String nombreClinica;
    private String contacto;
    private String photoUrl;
    @OneToMany(mappedBy = "clinica")
    private List<DireccionClinica> direccionClinicas = new ArrayList<>();
   @JsonIgnore
    @OneToMany(mappedBy = "clinica")
    private List<RegistroClinica> registroClinicas = new ArrayList<>();

    public Clinica() {
    }

    public Clinica(String nombreClinica, String contacto, String photoUrl, List<DireccionClinica> direccionClinicas) {
        this.nombreClinica = nombreClinica;
        this.contacto = contacto;
        this.photoUrl = photoUrl;
        this.direccionClinicas = direccionClinicas;
    }

    public Clinica(List<DireccionClinica> direccionClinicas, List<RegistroClinica> registroClinicas) {
        this.direccionClinicas = direccionClinicas;
        this.registroClinicas = registroClinicas;
    }

    public Integer getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(Integer clinicaId) {
        this.clinicaId = clinicaId;
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

    public List<DireccionClinica> getDireccionClinicas() {
        return direccionClinicas;
    }

    public void setDireccionClinicas(List<DireccionClinica> direccionClinicas) {
        this.direccionClinicas = direccionClinicas;
    }

    public List<RegistroClinica> getRegistroClinicas() {
        return registroClinicas;
    }

    public void setRegistroClinicas(List<RegistroClinica> registroClinicas) {
        this.registroClinicas = registroClinicas;
    }
}
