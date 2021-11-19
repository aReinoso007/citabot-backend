package com.citabot.domain;

public class Subspecialty {

    private int idSubspecialty;
    private String name;
    private Specialty specialty;

    public int getIdSubspecialty() {
        return idSubspecialty;
    }

    public void setIdSubspecialty(int idSubspecialty) {
        this.idSubspecialty = idSubspecialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
