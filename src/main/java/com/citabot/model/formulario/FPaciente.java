package com.citabot.model.formulario;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Date;

public class FPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private String username;
    private String estado;
    private String email;
    private String recoveryEmail;
    private String password;
    private String numeroContacto;


    private String tipoSangre;
    private String photoUrl;
    private String fechaNacimiento;
    private String genero;
    private String role;

    public FPaciente(String nombre, String apellido, String username, String estado, String email, String recoveryEmail, String password, String numeroContacto, String tipoSangre, String photoUrl, String fechaNacimiento, String genero, String role) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.estado = estado;
        this.email = email;
        this.recoveryEmail = recoveryEmail;
        this.password = password;
        this.numeroContacto = numeroContacto;
        this.tipoSangre = tipoSangre;
        this.photoUrl = photoUrl;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
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

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

