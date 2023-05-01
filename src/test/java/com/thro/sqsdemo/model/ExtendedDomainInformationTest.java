package com.thro.sqsdemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExtendedDomainInformationTest {
	
    @Test
    void ExtendedDomainInformation_GetSetDomainSuccess() {
        var dom = new ExtendedDomainInformation();
        dom.setDomain("google.com");
        assertEquals("google.com", dom.getDomain());
    }

    @Test
    void ExtendedDomainInformation_GetSetInformationFields() {
        var dom = new ExtendedDomainInformation();
        dom.addCustomInformation("Subdomain I", "translate.google.com");
        var information = dom.getInformationFields();
        
        assertEquals(1, information.size());
        assertEquals("Subdomain I", information.get(0).getName());
        assertEquals("translate.google.com", information.get(0).getValue());
    }
}
