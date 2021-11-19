package com.citabot.domain;

import java.sql.Timestamp;

public class User {
    private int idUser;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String emailRecovery;
    private String contrasena;
    private Timestamp creadoA;
    private Timestamp actualizadoA;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailRecovery() {
        return emailRecovery;
    }

    public void setEmailRecovery(String emailRecovery) {
        this.emailRecovery = emailRecovery;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Timestamp getCreadoA() {
        return creadoA;
    }

    public void setCreadoA(Timestamp creadoA) {
        this.creadoA = creadoA;
    }

    public Timestamp getActualizadoA() {
        return actualizadoA;
    }

    public void setActualizadoA(Timestamp actualizadoA) {
        this.actualizadoA = actualizadoA;
    }
}
