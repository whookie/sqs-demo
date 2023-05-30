package com.thro.sqsdemo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class ExtendedAddressInformation {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomInformationField> information = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CustomInformationField> getInformationFields() {
        return information;
    }

    public void setCustomInformation(List<CustomInformationField> information) {
        this.information = information;
    }

    public void addCustomInformation(String title, String value) {
        CustomInformationField field = new CustomInformationField();
        field.setName(title);
        field.setValue(value);
        field.setParentObject(this);
        
        information.add(field);
    }
}
