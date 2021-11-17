package com.citabot.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class DireccionClinica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Clinica clinica;
    @ManyToOne
    @JoinColumn
    private Direccion direccion;


}
