package com.citabot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Paciente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String photoUrl;
    private String tipoSangre;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Guayaquil")
    private Date fechaNacimiento;
    private String genero;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<PacientePatologia> pacientePatologias = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<PacienteCirugia> pacienteCirugias = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<DireccionPaciente> direccionPacientes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private List<Cita> citas = new ArrayList<>();

    private String role;


    public Paciente() {
        super();
    }

    public Paciente(String photoUrl, String tipoSangre, Date fechaNacimiento, String genero, List<PacientePatologia> pacientePatologias, List<PacienteCirugia> pacienteCirugias, List<DireccionPaciente> direccionPacientes, List<Cita> citas, String role) {
        this.photoUrl = photoUrl;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.pacientePatologias = pacientePatologias;
        this.pacienteCirugias = pacienteCirugias;
        this.direccionPacientes = direccionPacientes;
        this.citas = citas;
        this.role = role;
    }

    public Paciente(String username, String estado, String nombre, String apellido, String email, String recoveryEmail, String password, String numeroContacto, Timestamp createdAt, Timestamp updatedAt, String photoUrl, String tipoSangre, Date fechaNacimiento, String genero, List<PacientePatologia> pacientePatologias, List<PacienteCirugia> pacienteCirugias, List<DireccionPaciente> direccionPacientes, List<Cita> citas, String role) {
        super(username, estado, nombre, apellido, email, recoveryEmail, password, numeroContacto, createdAt, updatedAt);
        this.photoUrl = photoUrl;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.pacientePatologias = pacientePatologias;
        this.pacienteCirugias = pacienteCirugias;
        this.direccionPacientes = direccionPacientes;
        this.citas = citas;
        this.role = role;
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

    public List<DireccionPaciente> getDireccionPacientes() {
        return direccionPacientes;
    }

    public void setDireccionPacientes(List<DireccionPaciente> direccionPacientes) {
        this.direccionPacientes = direccionPacientes;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
