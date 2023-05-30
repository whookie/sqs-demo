package com.thro.sqsdemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IPAPITest {
    @Test
    void IPAPI_getAddressInformationTest() throws Exception {
        var result = IPAPI.getAddressInformation("1.1.1.1");
        var ip = result.getAsJsonObject().get("ip").getAsString();
        var org = result.getAsJsonObject().get("org").getAsString();

        assertEquals("1.1.1.1", ip);
        assertEquals("CLOUDFLARENET", org);
    }

    @Test
    void IPAPI_getAddressInformationTest_noSuchAddress() throws Exception {
        var result = IPAPI.getAddressInformation("256.0.0.1");

        var ip = result.getAsJsonObject().get("ip").getAsString();
        var error = result.getAsJsonObject().get("error").getAsBoolean();
        var reason = result.getAsJsonObject().get("reason").getAsString();

        assertEquals("256.0.0.1", ip);
        assertTrue(error);
        assertEquals("Invalid IP Address", reason);
    }
    
    @Test
    void IPAPI_getAddressInformationTest_reservedAddress() throws Exception {
        var result = IPAPI.getAddressInformation("240.0.0.1");

        var ip = result.getAsJsonObject().get("ip").getAsString();
        var error = result.getAsJsonObject().get("error").getAsBoolean();
        var reason = result.getAsJsonObject().get("reason").getAsString();

        assertEquals("240.0.0.1", ip);
        assertTrue(error);
        assertEquals("Reserved IP Address", reason);
    }
}
