package com.thro.sqsdemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class ValidatorsTest {
    @Test
    void Validator_preprocessNoSpaces() {
        String address = "1.1.1.1";
        String stripped = Validators.preprocessAddress(address);
        assertEquals(address, stripped);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.1.1.1", "  1.1.1.1  ", "\t1.1.1.1\t\t", "\n1.1.1.1\n"})
    void Validator_preprocessDomainInput(String arg) {
        String stripped = Validators.preprocessAddress(arg);
        assertEquals("1.1.1.1", stripped);
    }

    @Test
    void Validator_addressValid() {
        String domain = "1.1.1.1";
        assertTrue(Validators.validateAddress(domain));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.1.1.256", "1.1.1", "1.1.1,1", "1.1.1.1.1"})
    void Validator_addressInvalid(String arg) {
        assertFalse(Validators.validateAddress(arg));
    }
}
