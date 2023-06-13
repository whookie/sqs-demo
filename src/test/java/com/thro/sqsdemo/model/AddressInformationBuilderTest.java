package com.thro.sqsdemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class AddressInformationBuilderTest {

    @Test
    void AddressInformationBuilder_BuildAddressInformation() throws Exception {
        LinkedList<AddressEntry> entries = new LinkedList<AddressEntry>();
        entries.addLast(new AddressEntry("1.1.1.1", "Key A", "Value A"));
        entries.addLast(new AddressEntry("1.1.1.1", "Key B", "Value B"));
        entries.addLast(new AddressEntry("1.1.1.1", "Key C", "Value C"));
        
        AddressInformationBuilder builder = new AddressInformationBuilder(entries);
        var result = builder.getAllAddressFields();

        assertEquals(3, result.size());
        assertEquals("Value A", result.get("Key A"));
        assertEquals("Value B", result.get("Key B"));
        assertEquals("Value C", result.get("Key C"));
    }
}
