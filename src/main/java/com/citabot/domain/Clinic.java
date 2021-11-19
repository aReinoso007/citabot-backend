package com.citabot.domain;

import com.citabot.persistence.entity.Direccion;

import java.util.List;

public class Clinic {

    private int idClinic;
    private String name;
    private String contact;
    private String clinicPhotoURL;
    private List<Address> addresses;


    public int getIdClinic() {
        return idClinic;
    }

    public void setIdClinic(int idClinic) {
        this.idClinic = idClinic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getClinicPhotoURL() {
        return clinicPhotoURL;
    }

    public void setClinicPhotoURL(String clinicPhotoURL) {
        this.clinicPhotoURL = clinicPhotoURL;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
