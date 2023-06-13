package com.thro.sqsdemo.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AddressEntryTest {
    
    @Autowired
    AddressInformationRepo repo;

    @Test
    public void AddressEntryTest_CreateRetrieveSingleEntry_Success() throws Exception {
        AddressEntry entry = new AddressEntry();
        entry.setAddress("1.1.1.1");
        entry.setKey("Key");
        entry.setValue("Value");

        repo.save(entry);

        var retrieved = repo.findByAddressAndKey("1.1.1.1", "Key");
        
        assertTrue(retrieved.isPresent());
        assertEquals("1.1.1.1", retrieved.get().getAddress());
        assertEquals("Key", retrieved.get().getKey());
        assertEquals("Value", retrieved.get().getValue());
    }

    @Test
    public void AddressEntryTest_RetrieveSingleEntry_FailureNoEntry() throws Exception {
        var result = repo.findByAddressAndKey("1.1.1.1", "NoSuchKey");
        assertFalse(result.isPresent());
    }

    @Test
    public void AddressEntryTest_CreateRetrieveMultipleEntries() throws Exception {
        AddressEntry entry1 = new AddressEntry("1.1.1.1", "Key A", "Value A");
        AddressEntry entry2 = new AddressEntry("1.1.1.1", "Key B", "Value B");

        repo.save(entry1);
        repo.save(entry2);

        var result = repo.findAllByAddress("1.1.1.1");

        assertEquals(2, result.size());
        assertEquals("1.1.1.1", result.get(0).getAddress());
        assertEquals("1.1.1.1", result.get(1).getAddress());
        assertEquals("Key A", result.get(0).getKey());
        assertEquals("Key B", result.get(1).getKey());
        assertEquals("Value A", result.get(0).getValue());
        assertEquals("Value B", result.get(1).getValue());
    }

    @Test
    public void AddressEntryTest_CreateDeleteSingleEntry_Success() throws Exception {
        AddressEntry entry = new AddressEntry("1.1.1.1", "Key", "Value");
        repo.save(entry);
        
        assertDoesNotThrow(() -> repo.deleteByAddressAndKey("1.1.1.1", "Key"));
        
        var result = repo.findByAddressAndKey("1.1.1.1", "Key");
        assertFalse(result.isPresent());
    }

    @Test
    public void AddressEntryTest_CreateSingleAndDelete_Success() throws Exception {
        AddressEntry entry = new AddressEntry("1.1.1.1", "Key", "Value");
        repo.save(entry);
        
        var result = repo.findByAddressAndKey("1.1.1.1", "Key");
        assertTrue(result.isPresent());

        repo.deleteByAddressAndKey("1.1.1.1", "Key");
        var noResult = repo.findByAddressAndKey("1.1.1.1", "Key");
        assertFalse(noResult.isPresent());
    }
}
