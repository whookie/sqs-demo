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
public class ExtendedDomainInformation {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String domain;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<CustomInformationField> information = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<CustomInformationField> getInformationFields() {
        return information;
    }

    public void addCustomInformation(String title, String value) {
        CustomInformationField field = new CustomInformationField();
        field.setName(title);
        field.setValue(value);
        field.setParentObject(this);
        
        information.add(field);
    }
}
