package com.citabot.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Paciente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String photoUrl;
    private String tipoSangre;
    private Date fechaNacimiento;
    private String contactPhoneNumber;
    private String genero;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<PacientePatologia> pacientePatologias = new ArrayList<>();
    @OneToMany(mappedBy = "paciente")
    private List<PacienteCirugia> pacienteCirugias = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private List<Cita> citas = new ArrayList<>();

    public Paciente() {
        super();
    }

    public Paciente(String username, String estado, String nombre, String apellido, String email, String recoveryEmail, String password, String numeroContacto, Timestamp createdAt, Timestamp updatedAt, String photoUrl, String tipoSangre, Date fechaNacimiento, String contactPhoneNumber, String genero, List<PacientePatologia> pacientePatologias, List<PacienteCirugia> pacienteCirugias, List<Cita> citas) {
        super(username, estado, nombre, apellido, email, recoveryEmail, password, numeroContacto, createdAt, updatedAt);
        this.photoUrl = photoUrl;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.contactPhoneNumber = contactPhoneNumber;
        this.genero = genero;
        this.pacientePatologias = pacientePatologias;
        this.pacienteCirugias = pacienteCirugias;
        this.citas = citas;
    }

    public Paciente(String photoUrl, String tipoSangre, Date fechaNacimiento, String contactPhoneNumber, String genero, List<PacientePatologia> pacientePatologias, List<PacienteCirugia> pacienteCirugias, List<Cita> citas) {
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
    public Paciente(List<PacientePatologia> pacientePatologias, List<PacienteCirugia> pacienteCirugias, List<Cita> citas) {
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

    public List<PacientePatologia> getPacientePatologias() {
        return pacientePatologias;
    }

    public void setPacientePatologias(List<PacientePatologia> pacientePatologias) {
        this.pacientePatologias = pacientePatologias;
    }

    public List<PacienteCirugia> getPacienteCirugias() {
        return pacienteCirugias;
    }

    public void setPacienteCirugias(List<PacienteCirugia> pacienteCirugias) {
        this.pacienteCirugias = pacienteCirugias;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
