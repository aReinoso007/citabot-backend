package com.citabot.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class RegistroClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Medico medico;
    @ManyToOne
    @JoinColumn
    private Clinica clinica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroClinica")
    private Set<Cita>   citas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroClinica")
    private Set<Horario> horarios;

    public RegistroClinica() {
    }

    /*Asignacion incial de clinica a medico y el horario*/
    public RegistroClinica(Medico medico, Clinica clinica, Set<Horario> horarios) {
        this.medico = medico;
        this.clinica = clinica;
        this.horarios = horarios;
    }

    /*Para registrar citas medicas */
    public RegistroClinica(Set<Cita> citas) {
        this.citas = citas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }
}
