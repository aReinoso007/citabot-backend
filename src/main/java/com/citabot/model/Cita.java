package com.citabot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Cita implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citaId;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Date fechaCita;
    private Time horaInicio;
    private Time horaFin;
    private String sintomas;
    private BigDecimal precioConsulta;
    private String estado;
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private RegistroClinica clinicaMedico;

    public Cita() {
    }

    public Cita(Timestamp createdAt, Timestamp updateAt, Date fechaCita, Time horaInicio, Time horaFin, String sintomas, BigDecimal precioConsulta, String estado, Paciente paciente, RegistroClinica clinicaMedico) {
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.fechaCita = fechaCita;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.sintomas = sintomas;
        this.precioConsulta = precioConsulta;
        this.estado = estado;
        this.paciente = paciente;
        this.clinicaMedico = clinicaMedico;
    }

    public Integer getCitaId() {
        return citaId;
    }

    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public BigDecimal getPrecioConsulta() {
        return precioConsulta;
    }

    public void setPrecioConsulta(BigDecimal precioConsulta) {
        this.precioConsulta = precioConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public RegistroClinica getClinicaMedico() {
        return clinicaMedico;
    }

    public void setClinicaMedico(RegistroClinica clinicaMedico) {
        this.clinicaMedico = clinicaMedico;
    }
}
