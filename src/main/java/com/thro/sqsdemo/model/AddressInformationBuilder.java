package com.thro.sqsdemo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressInformationBuilder {
    
    private List<AddressEntry> entries;

    public AddressInformationBuilder(List<AddressEntry> entries) {
        this.entries = entries;
    }

    public Map<String, String> getAllAddressFields() {
        Map<String, String> result = new HashMap<String, String>();

        for (AddressEntry entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
