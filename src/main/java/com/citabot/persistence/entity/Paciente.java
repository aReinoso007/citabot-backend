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
    private Date fechaNacimiento;
    private String contactPhoneNumber;
    private String genero;
    @OneToMany(mappedBy = "paciente")
    private Set<PacientePatologia> pacientePatologias;
    @OneToMany(mappedBy = "paciente")
    private Set<PacienteCirugia> pacienteCirugias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private Set<Cita> citas;

    public Paciente() {
        super();
    }

    public Paciente(String username, String nombre, String apellido, String email, String recoveryEmail, String password, String numeroContacto, Timestamp createdAt, Timestamp updatedAt, String photoUrl, String tipoSangre, Date fechaNacimiento, String contactPhoneNumber, String genero, Set<PacientePatologia> pacientePatologias, Set<PacienteCirugia> pacienteCirugias, Set<Cita> citas) {
        super(username, nombre, apellido, email, recoveryEmail, password, numeroContacto, createdAt, updatedAt);
        this.photoUrl = photoUrl;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.contactPhoneNumber = contactPhoneNumber;
        this.genero = genero;
        this.pacientePatologias = pacientePatologias;
        this.pacienteCirugias = pacienteCirugias;
        this.citas = citas;
    }

    public Paciente(String photoUrl, String tipoSangre, Date fechaNacimiento, String contactPhoneNumber, String genero, Set<PacientePatologia> pacientePatologias, Set<PacienteCirugia> pacienteCirugias, Set<Cita> citas) {
        this.photoUrl = photoUrl;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.contactPhoneNumber = contactPhoneNumber;
        this.genero = genero;
        this.pacientePatologias = pacientePatologias;
        this.pacienteCirugias = pacienteCirugias;
        this.citas = citas;
    }
    /*Para agregar patologia, cirugias y citas */
    public Paciente(Set<PacientePatologia> pacientePatologias, Set<PacienteCirugia> pacienteCirugias, Set<Cita> citas) {
        this.pacientePatologias = pacientePatologias;
        this.pacienteCirugias = pacienteCirugias;
        this.citas = citas;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }
}
