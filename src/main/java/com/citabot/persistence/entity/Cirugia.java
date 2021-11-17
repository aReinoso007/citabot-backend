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
    private Integer id;
    private Date fechaProcedimiento;
    private String descripcion;

    public Cirugia() {
    }

    public Cirugia(Date fechaProcedimiento, String descripcion) {
        this.fechaProcedimiento = fechaProcedimiento;
        this.descripcion = descripcion;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
