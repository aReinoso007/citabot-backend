package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class DireccionPaciente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    @ManyToOne
    @JoinColumn
    private Paciente paciente;
    @ManyToOne
    @JoinColumn
    private Direccion direccion;

}
