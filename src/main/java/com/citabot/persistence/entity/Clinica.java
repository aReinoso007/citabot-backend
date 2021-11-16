package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreClinica;
    private String contacto;
    private String photoUrl;



}
