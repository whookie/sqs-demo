package com.thro.sqsdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CustomInformationField {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ExtendedAddressInformation address;

    @Column(nullable = false, unique = true)
    private String fieldName;

    @Column(nullable = false)
    private String fieldValue;

    public Long getId() {
        return id;
    }

    public String getName() {
        return fieldName;
    }

    public String getValue() {
        return fieldValue;
    }

    public void setName(String name) {
        this.fieldName = name;
    }

    public void setValue(String value) {
        this.fieldValue = value;
    }

    public void setParentObject(ExtendedAddressInformation address) {
        this.address = address;
    }

    public ExtendedAddressInformation getParentObject() {
        return this.address;
    }
}
