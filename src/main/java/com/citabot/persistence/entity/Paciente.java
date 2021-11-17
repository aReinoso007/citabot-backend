package com.citabot.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Paciente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String photoUrl;
    private String tipoSangre;
    private Date birthDate;
    private String emergencyContactName;
    private String contactPhoneNumber;
    private String contactRelationship;
    @OneToMany(mappedBy = "paciente")
    private Set<PacientePatologia> pacientePatologias;
    @OneToMany(mappedBy = "paciente")
    private Set<PacienteCirugia> pacienteCirugias;

    public Paciente() {
        super();
    }

    public Paciente(String username, String nombre, String apellido, String email, String recoveryEmail, String password, String numeroContacto, Timestamp createdAt, Timestamp updatedAt, String photoUrl, String tipoSangre, Date birthDate, String emergencyContactName, String contactPhoneNumber, String contactRelationship, Set<PacientePatologia> pacientePatologias, Set<PacienteCirugia> pacienteCirugias) {
        super(username, nombre, apellido, email, recoveryEmail, password, numeroContacto, createdAt, updatedAt);
        this.photoUrl = photoUrl;
        this.tipoSangre = tipoSangre;
        this.birthDate = birthDate;
        this.emergencyContactName = emergencyContactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactRelationship = contactRelationship;
        this.pacientePatologias = pacientePatologias;
        this.pacienteCirugias = pacienteCirugias;
    }

    public Paciente(String photoUrl, String tipoSangre, Date birthDate, String emergencyContactName, String contactPhoneNumber, String contactRelationship, Set<PacientePatologia> pacientePatologias, Set<PacienteCirugia> pacienteCirugias) {
        this.photoUrl = photoUrl;
        this.tipoSangre = tipoSangre;
        this.birthDate = birthDate;
        this.emergencyContactName = emergencyContactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactRelationship = contactRelationship;
        this.pacientePatologias = pacientePatologias;
        this.pacienteCirugias = pacienteCirugias;
    }

    public Paciente(String photoUrl, String tipoSangre, Date birthDate, String emergencyContactName, String contactPhoneNumber, String contactRelationship) {
        this.photoUrl = photoUrl;
        this.tipoSangre = tipoSangre;
        this.birthDate = birthDate;
        this.emergencyContactName = emergencyContactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactRelationship = contactRelationship;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactRelationship() {
        return contactRelationship;
    }

    public void setContactRelationship(String contactRelationship) {
        this.contactRelationship = contactRelationship;
    }

    public Set<PacientePatologia> getPacientePatologias() {
        return pacientePatologias;
    }

    public void setPacientePatologias(Set<PacientePatologia> pacientePatologias) {
        this.pacientePatologias = pacientePatologias;
    }

    public Set<PacienteCirugia> getPacienteCirugias() {
        return pacienteCirugias;
    }

    public void setPacienteCirugias(Set<PacienteCirugia> pacienteCirugias) {
        this.pacienteCirugias = pacienteCirugias;
    }
}
