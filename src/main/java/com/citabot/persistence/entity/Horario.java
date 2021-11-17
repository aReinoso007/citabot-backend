package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
}
