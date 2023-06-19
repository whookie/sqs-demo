package com.thro.sqsdemo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility-class to create a map of key-value pairs from a list of address entries
 */
public class AddressInformationBuilder {
    
    private List<AddressEntry> entries;

    /**
     * Constructor
     * @param entries List of AddressEntry retrieved from the database
     */
    public AddressInformationBuilder(List<AddressEntry> entries) {
        this.entries = entries;
    }

    /**
     * Get all address fields in a GSON-serializable structure
     * @return All key-value pairs as map
     */
    public Map<String, String> getAllAddressFields() {
        Map<String, String> result = new HashMap<>();

        for (AddressEntry entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
