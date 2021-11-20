package com.citabot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
public class RegistroClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registroClinicaId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="medico_id")
    private Medico medico;
    @ManyToOne /*Esto nos dice que no vamos a insertar una nueva clinica desde el registro del medico */
    @JsonIgnore
    @JoinColumn(name ="clinica_id")
    private Clinica clinica;
    /*En estas no se agrega eso puesto que crean aqui las citas */
    @OneToMany
    private List<Cita> citas;
    /*En estas no se agrega eso puesto que crean aqui los horarios */
    @OneToMany()
    private Set<Horario> horarios;

    public RegistroClinica() {
    }

    /*Asignacion incial de clinica a medico y el horario*/

    public RegistroClinica(Timestamp createdAt, Timestamp updatedAt, Medico medico, Clinica clinica, List<Cita> citas, Set<Horario> horarios) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.medico = medico;
        this.clinica = clinica;
        this.citas = citas;
        this.horarios = horarios;
    }

    /*Para registrar citas medicas */
    public RegistroClinica(List<Cita> citas) {
        this.citas = citas;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRegistroClinicaId() {
        return registroClinicaId;
    }

    public void setRegistroClinicaId(Integer registroClinicaId) {
        this.registroClinicaId = registroClinicaId;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }
}
