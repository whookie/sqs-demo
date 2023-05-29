package com.thro.sqsdemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatorsTest {
    @Test
    void Validator_preprocessNoSpaces() {
        String domain = "domain.com";
        String stripped = Validators.preprocessDomain(domain);
        assertEquals(domain, stripped);
    }

    @ParameterizedTest
    @ValueSource(strings = {"domain.com", "  domain.com  ", "\tdomain.com\t\t", "\ndomain.com\n"})
    void Validator_preprocessDomainInput(String arg) {
        String stripped = Validators.preprocessDomain(arg);
        assertEquals("domain.com", stripped);
    }

    @Test
    void Validator_domainValid() {
        String domain = "domain.com";
        assertTrue(Validators.validateDomain(domain));
    }

    @ParameterizedTest
    @ValueSource(strings = {"domain:com", "domain.unknowntld", "domain.local", "1.1.1.1"})
    void Validator_domainsInvalid(String arg) {
        assertFalse(Validators.validateDomain(arg));
    }
}
