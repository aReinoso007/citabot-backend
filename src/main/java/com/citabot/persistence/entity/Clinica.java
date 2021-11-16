package com.citabot.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreClinica;
    private String contacto;
    private String photoUrl;

    @OneToOne(mappedBy = "clinica", cascade = CascadeType.ALL)
    @JoinColumn
    private Direccion direccion;

    @OneToMany(mappedBy = "clinica")
    private Set<MedicoClinica> medicosRegistrados;


}
