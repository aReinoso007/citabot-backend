package com.citabot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

@Entity
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer horarioId;
    @ManyToOne
    @JoinColumn
    private RegistroClinica registroClinica;
    private Time duracionCita;
    private String dia;
    private Time inicio;
    private Time fin;

    public Horario() {
    }

    public Horario(RegistroClinica registroClinica, Time duracionCita, String dia, Time inicio, Time fin) {
        this.registroClinica = registroClinica;
        this.duracionCita = duracionCita;
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Integer getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Integer horarioId) {
        this.horarioId = horarioId;
    }

    public RegistroClinica getRegistroClinica() {
        return registroClinica;
    }

    public void setRegistroClinica(RegistroClinica registroClinica) {
        this.registroClinica = registroClinica;
    }

    public Time getDuracionCita() {
        return duracionCita;
    }

    public void setDuracionCita(Time duracionCita) {
        this.duracionCita = duracionCita;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Time getInicio() {
        return inicio;
    }

    public void setInicio(Time inicio) {
        this.inicio = inicio;
    }

    public Time getFin() {
        return fin;
    }

    public void setFin(Time fin) {
        this.fin = fin;
    }
}
