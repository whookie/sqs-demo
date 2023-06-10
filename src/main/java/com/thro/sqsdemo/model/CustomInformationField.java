package com.thro.sqsdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "custom_information_fields", uniqueConstraints = {
    @UniqueConstraint(name = "unique_address_fieldName", columnNames = {"address", "field_name"})
})
public class CustomInformationField {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address")
    private ExtendedAddressInformation address;

    @Column(name = "field_name", nullable = false)
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
