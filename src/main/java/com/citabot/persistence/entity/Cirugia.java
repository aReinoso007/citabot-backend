package com.citabot.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

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
