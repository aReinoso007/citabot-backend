package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class MedicoEducacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer educacionId;

    private Medico medico;


}
