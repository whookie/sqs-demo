package com.thro.sqsdemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomInformationFieldTest {
    @Test
    void CustomInformationField_GetSetName() {
        var field = new CustomInformationField();
        field.setName("Owner");
        assertEquals("Owner", field.getName());
    }

    @Test
    void CustomInformationField_GetSetValue() {
        var field = new CustomInformationField();
        field.setValue("Somebody");
        assertEquals("Somebody", field.getValue());
    }

    @Test
    void CustomInformationField_GetSetParentObject() {
        var parent = new ExtendedDomainInformation();
        var field = new CustomInformationField();
        field.setParentObject(parent);
        assertEquals(parent, field.getParentObject());
    }
}
