package com.citabot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cirugia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cirugiaId;
    private Date fechaProcedimiento;
    private String descripcion;

    public Cirugia() {
    }

    public Cirugia(Date fechaProcedimiento, String descripcion) {
        this.fechaProcedimiento = fechaProcedimiento;
        this.descripcion = descripcion;
    }


    public Integer getCirugiaId() {
        return cirugiaId;
    }

    public void setCirugiaId(Integer cirugiaId) {
        this.cirugiaId = cirugiaId;
    }

    public Date getFechaProcedimiento() {
        return fechaProcedimiento;
    }

    public void setFechaProcedimiento(Date fechaProcedimiento) {
        this.fechaProcedimiento = fechaProcedimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
