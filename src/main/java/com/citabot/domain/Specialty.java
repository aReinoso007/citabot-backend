package com.citabot.domain;

import java.util.List;

public class Specialty {

    private int idSpecialty;
    private String name;
    private List<Subspecialty> subspecialties;

    public int getidSpecialty() {
        return idSpecialty;
    }

    public void setidSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subspecialty> getSubspecialties() {
        return subspecialties;
    }

    public void setSubspecialties(List<Subspecialty> subspecialties) {
        this.subspecialties = subspecialties;
    }
}
