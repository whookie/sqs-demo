package com.thro.sqsdemo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "address_information")
@IdClass(AddressEntryId.class)
public class AddressEntry {

    @Id
    @Column(name = "address")
    private String address;
    
    // field_name and field_value can't be saved as "key" and "value", as those statements destroy the SQL queries
    @Id
    @Column(name = "field_name")
    private String key;
    
    @Column(name = "field_value")
    private String value;

    public AddressEntry() {}

    public AddressEntry(String address, String key, String value) {
        this.address = address;
        this.key = key;
        this.value = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

