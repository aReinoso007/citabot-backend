package com.citabot.model;

import javax.persistence.*;
import java.io.Serializable;
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
    private Duration duracionCita;
    private String dia;
    private LocalTime inicio;
    private LocalTime fin;

    public Horario() {
    }

    public Horario(Integer horarioId, RegistroClinica registroClinica, Duration duracionCita, String dia, LocalTime inicio, LocalTime fin) {
        this.horarioId = horarioId;
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

    public Duration getDuracionCita() {
        return duracionCita;
    }

    public void setDuracionCita(Duration duracionCita) {
        this.duracionCita = duracionCita;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }
}
