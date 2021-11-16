package com.citabot.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String recoveryEmail;
    private String password;
    private String gender;
    private String telephoneNumber;
    private String cellphoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Usuario(Integer usuarioId, String username, String name, String lastName, String email, String recoveryEmail, String password, String gender, String telephoneNumber, String cellphoneNumber, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.recoveryEmail = recoveryEmail;
        this.password = password;
        this.gender = gender;
        this.telephoneNumber = telephoneNumber;
        this.cellphoneNumber = cellphoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Usuario() {

    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
