package com.thro.sqsdemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExtendedDomainInformationTest {
	
    @Test
    void ExtendedDomainInformation_GetSetDomainSuccess() {
        var dom = new ExtendedDomainInformation();
        dom.setDomain("google.com");
        assertEquals(dom.getDomain(), "google.com");
    }

    @Test
    void ExtendedDomainInformation_GetSetInformationFields() {
        var dom = new ExtendedDomainInformation();
        dom.addCustomInformation("Subdomain I", "translate.google.com");
        var information = dom.getInformationFields();
        
        assertEquals(information.size(), 1);
        assertEquals(information.get(0).getName(), "Subdomain I");
        assertEquals(information.get(0).getValue(), "translate.google.com");
    }
}
