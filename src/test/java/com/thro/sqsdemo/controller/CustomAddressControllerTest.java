package com.thro.sqsdemo.controller;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.JsonParser;

import org.springframework.http.MediaType;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomAddressControllerTest {
    
    @Autowired
    private MockMvc application;

    @Test
    void AddressController_GetNoAddressTest() throws Exception {
        final String api_response_string = "{\r\n    \"ip\": \"10.10.10.10\",\r\n    \"network\": \"10.10.10.0/24\",\r\n    \"version\": \"IPv4\",\r\n    \"city\": \"Sydney\",\r\n    \"region\": \"New South Wales\",\r\n    \"region_code\": \"NSW\",\r\n    \"country\": \"AU\",\r\n    \"country_name\": \"Australia\",\r\n    \"country_code\": \"AU\",\r\n    \"country_code_iso3\": \"AUS\",\r\n    \"country_capital\": \"Canberra\",\r\n    \"country_tld\": \".au\",\r\n    \"continent_code\": \"OC\",\r\n    \"in_eu\": false,\r\n    \"postal\": \"2000\",\r\n    \"latitude\": -33.859336,\r\n    \"longitude\": 151.203624,\r\n    \"timezone\": \"Australia/Sydney\",\r\n    \"utc_offset\": \"+1000\",\r\n    \"country_calling_code\": \"+61\",\r\n    \"currency\": \"AUD\",\r\n    \"currency_name\": \"Dollar\",\r\n    \"languages\": \"en-AU\",\r\n    \"country_area\": 7686850.0,\r\n    \"country_population\": 24992369,\r\n    \"asn\": \"AS13335\",\r\n    \"org\": \"CLOUDFLARENET\"\r\n}";
        var api_response = JsonParser.parseString(api_response_string);

        try (MockedConstruction<IPAPI> mocked = mockConstruction(IPAPI.class, (mock, context) -> { when(mock.getAddressInformation()).thenReturn(api_response); })) {
            IPAPI api = new IPAPI("10.10.10.10");
            when(api.getAddressInformation()).thenReturn(api_response);

            this.application.perform(get("/address/get").param("address", "10.10.10.10")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            ).andExpect(status().is(404));
        }
    }

    @Test
    void AddressController_AddOneAddressTest() throws Exception {
        this.application.perform(post("/address/add").param("address", "240.0.0.2")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
    }

    @Test
    void AddressController_AddDuplicateAddressTest() throws Exception {
        this.application.perform(post("/address/add").param("address", "240.0.0.3")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));

        this.application.perform(post("/address/add").param("address", "240.0.0.3")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(409));
    }

    @Test
    void AddressController_GetAddress_AddressFound() throws Exception {
        final String api_response_string = "{\r\n    \"ip\": \"2.2.2.2\",\r\n    \"network\": \"2.2.2.0/24\",\r\n    \"version\": \"IPv4\",\r\n    \"city\": \"Sydney\",\r\n    \"region\": \"New South Wales\",\r\n    \"region_code\": \"NSW\",\r\n    \"country\": \"AU\",\r\n    \"country_name\": \"Australia\",\r\n    \"country_code\": \"AU\",\r\n    \"country_code_iso3\": \"AUS\",\r\n    \"country_capital\": \"Canberra\",\r\n    \"country_tld\": \".au\",\r\n    \"continent_code\": \"OC\",\r\n    \"in_eu\": false,\r\n    \"postal\": \"2000\",\r\n    \"latitude\": -33.859336,\r\n    \"longitude\": 151.203624,\r\n    \"timezone\": \"Australia/Sydney\",\r\n    \"utc_offset\": \"+1000\",\r\n    \"country_calling_code\": \"+61\",\r\n    \"currency\": \"AUD\",\r\n    \"currency_name\": \"Dollar\",\r\n    \"languages\": \"en-AU\",\r\n    \"country_area\": 7686850.0,\r\n    \"country_population\": 24992369,\r\n    \"asn\": \"AS13335\",\r\n    \"org\": \"CLOUDFLARENET\"\r\n}";
        var api_response = JsonParser.parseString(api_response_string);

        try (MockedConstruction<IPAPI> mocked = mockConstruction(IPAPI.class, (mock, context) -> { when(mock.getAddressInformation()).thenReturn(api_response); })) {
            this.application.perform(post("/address/add").param("address", "2.2.2.2")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            ).andExpect(status().is(200));
            
            this.application.perform(get("/address/get").param("address", "2.2.2.2")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            ).andExpect(status().is(200));
        }
    }

    @Test
    void AddressController_GetAddress_AddressMalformed() throws Exception {
            this.application.perform(get("/address/get").param("address", "555.2.2.2")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            ).andExpect(status().is(400));
    }
    
    @Test
    void AddressController_GetAddress_AddressIsReserved() throws Exception {
        final String api_response_string = "{\r\n    \"ip\": \"10.10.10.10\",\r\n    \"error\": true,\r\n    \"reason\": \"Reserved IP Address\",\r\n    \"reserved\": true,\r\n    \"version\": \"IPv4\"\r\n}";
        var api_response = JsonParser.parseString(api_response_string);

        try (MockedConstruction<IPAPI> mocked = mockConstruction(IPAPI.class, (mock, context) -> { when(mock.getAddressInformation()).thenReturn(api_response); })) {
            this.application.perform(get("/address/get").param("address", "2.2.2.2")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
            ).andExpect(status().is(417));
        }
    }
    
    @Test
    void AddressController_AddInvalidAddress() throws Exception {
        this.application.perform(post("/address/add").param("address", "256.0.0.1")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(400));
    }

    @Test
    void AddressController_AddGetValidAddressWithFields() throws Exception {
        this.application.perform(post("/address/add").param("address", "1.1.1.1")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(200));
    }

    @Test
    void AddressController_GetAddressInformation_AddressMalformed() throws Exception {
        this.application.perform(post("/address/add").param("address", "256.1.1.1")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        ).andExpect(status().is(400));
    }
}
