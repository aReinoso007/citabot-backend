package com.citabot.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Cita implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Date fechaCita;
    private Time horaInicio;
    private Time horaFin;
    private String motivoConsulta;
    private String sintomas;
    private BigDecimal precioConsulta;
    private String estado;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private RegistroClinica clinicaMedico;

}
