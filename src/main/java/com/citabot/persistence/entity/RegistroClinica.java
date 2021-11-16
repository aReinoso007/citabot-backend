package com.citabot.persistence.entity;

import javax.persistence.*;

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

}
