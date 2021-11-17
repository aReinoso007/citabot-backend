package com.citabot.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Paciente extends Usuario{

    private String photoUrl;
    private String tipoSangre;
    private Date birthDate;
    private String emergencyContactName;
    private String contactPhoneNumber;
    private String contactRelationship;

    public Paciente(String username, String name, String lastName, String email, String recoveryEmail, String password, String gender, String telephoneNumber, String cellphoneNumber, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(username, name, lastName, email, recoveryEmail, password, telephoneNumber, cellphoneNumber, createdAt, updatedAt);
    }

    public Paciente() {
        super();
    }
}
