package com.thro.sqsdemo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExtendedDomainInformationTest {
	
    @Test
    void ExtendedDomainInformation_GetSetDomainSuccess() {
        var dom = new ExtendedAddressInformation();
        dom.setAddress("google.com");
        assertEquals("google.com", dom.getAddress());
    }

    @Test
    void ExtendedDomainInformation_GetAddInformationFields() {
        var dom = new ExtendedAddressInformation();
        dom.addCustomInformation("Subdomain I", "translate.google.com");
        var information = dom.getInformationFields();
        
        assertEquals(1, information.size());
        assertEquals("Subdomain I", information.get(0).getName());
        assertEquals("translate.google.com", information.get(0).getValue());
    }

    @Test
    void ExtendedDomainInformation_GetSetInformationFields() {
        var dom = new ExtendedAddressInformation();
        var info = new CustomInformationField();
        info.setName("Subdomain I");
        info.setValue("translate.google.com");
        ArrayList<CustomInformationField> informationList = new ArrayList<CustomInformationField>();
        informationList.add(info);
        dom.setCustomInformation(informationList);
        var information = dom.getInformationFields();
        
        assertEquals(1, information.size());
        assertEquals("Subdomain I", information.get(0).getName());
        assertEquals("translate.google.com", information.get(0).getValue());
    }
}
