package com.thro.sqsdemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatorsTest {
    @Test
    void Validator_preprocessNoSpaces() {
        String domain = "domain.com";
        String stripped = Validators.preprocessDomain(domain);
        assertEquals(domain, stripped);
    }

    @Test
    void Validator_preprocessPrePostSpaces() {
        String domain = "  domain.com   ";
        String stripped = Validators.preprocessDomain(domain);
        assertEquals("domain.com", stripped);
    }
    
    @Test
    void Validator_preprocessPrePostTabs() {
        String domain = "\tdomain.com\t\t";
        String stripped = Validators.preprocessDomain(domain);
        assertEquals("domain.com", stripped);
    }
    
    @Test
    void Validator_preprocessPrePostNewline() {
        String domain = "\ndomain.com\n";
        String stripped = Validators.preprocessDomain(domain);
        assertEquals("domain.com", stripped);
    }

    @Test
    void Validator_domainValid() {
        String domain = "domain.com";
        assertTrue(Validators.validateDomain(domain));
    }

    @Test
    void Validator_domainInvalid_malformed() {
        String domain = "domain:com";
        assertFalse(Validators.validateDomain(domain));
    }

    @Test
    void Validator_domainInvalid_unknownTld() {
        String domain = "domain.unknowntld";
        assertFalse(Validators.validateDomain(domain));
    }

    @Test
    void Validator_domainInvalid_LocalDomain() {
        String domain = "domain.local";
        assertFalse(Validators.validateDomain(domain));
    }

    @Test
    void Validator_domainInvalid_IpAddress() {
        String domain = "1.1.1.1";
        assertFalse(Validators.validateDomain(domain));
    }
}
