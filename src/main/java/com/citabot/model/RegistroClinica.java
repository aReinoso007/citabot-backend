package com.citabot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class RegistroClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registroClinicaId;
    @ManyToOne
    @JoinColumn(name ="medico_id", insertable = false, updatable = false)
    private Medico medico;
    @ManyToOne /*Esto nos dice que no vamos a insertar una nueva clinica desde el registro del medico */
    @JoinColumn(name ="clinica_id", insertable = false, updatable = false)
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
    public RegistroClinica(Medico medico, Clinica clinica, Set<Horario> horarios) {
        this.medico = medico;
        this.clinica = clinica;
        this.horarios = horarios;
    }

    /*Para registrar citas medicas */
    public RegistroClinica(List<Cita> citas) {
        this.citas = citas;
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
