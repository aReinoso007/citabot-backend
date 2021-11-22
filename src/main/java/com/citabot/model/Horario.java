package com.citabot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;

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
    private LocalDateTime inicio;
    private LocalDateTime fin;

    public Horario() {
    }

    public Horario(RegistroClinica registroClinica, Duration duracionCita, String dia, LocalDateTime inicio, LocalDateTime fin) {
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

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }
}
