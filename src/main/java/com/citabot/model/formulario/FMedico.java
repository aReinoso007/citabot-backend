package com.citabot.model.formulario;

import java.io.Serializable;
import java.sql.Timestamp;

public class FMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private String username;
    private String estado;
    private String email;
    private String recoveryEmail;
    private String password;
    private String numeroContacto;

    private String slogan;
    private String profesion;
    private String photoUrl;
    private String descripcion;
    private String role;

    public FMedico(String nombre, String apellido, String username, String estado, String email, String recoveryEmail, String password, String numeroContacto, String slogan, String profesion, String photoUrl, String descripcion, String role) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.estado = estado;
        this.email = email;
        this.recoveryEmail = recoveryEmail;
        this.password = password;
        this.numeroContacto = numeroContacto;
        this.slogan = slogan;
        this.profesion = profesion;
        this.photoUrl = photoUrl;
        this.descripcion = descripcion;
        this.role = role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail = recoveryEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
