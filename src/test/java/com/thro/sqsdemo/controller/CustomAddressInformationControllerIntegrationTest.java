package com.thro.sqsdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
class CustomAddressInformationControllerIntegrationTest {
    
    @Autowired
    private MockMvc mvc;

    // Unfortunately, we need to mock the API as otherwise the API might block us with "Rate Limit Exceeded" during testing
    @MockBean
    private IIPAPI api;

    @Test
    void CustomAddressController_addInformationField_invalidAddress() throws Exception {
        mvc.perform(post("/information/add?address=Text&name=Key A&Value=Value A"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void CustomAddressController_deleteInformationField_invalidAddress() throws Exception {
        mvc.perform(delete("/information/delete?address=97802347869654&name=Key A"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void CustomAddressController_getInformationField_invalidAddress() throws Exception {
        mvc.perform(get("/information/get?address=1..1.1.1"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void CustomAddressController_getInformationField_apiNotReachable() throws Exception {
        when(api.getAddressInformation("1.1.1.1")).thenThrow(IOException.class);

        mvc.perform(post("/information/add?address=1.1.1.1&name=Key A&value=Value A"))
            .andExpect(status().isOk());

        mvc.perform(get("/information/get?address=1.1.1.1"))
            .andExpect(status().isServiceUnavailable());
    }

    @Test
    void CustomAddressController_getInformationField_noFields() throws Exception {
        mvc.perform(get("/information/get?address=1.1.1.1"))
            .andExpect(status().isNotFound());
    }

    @Test
    void CustomAddressController_addGetInformationField() throws Exception {
        String sExpectedResponse = "{\"ip\":\"1.1.1.1\",\"network\":\"1.1.1.0/24\",\"version\":\"IPv4\",\"city\":\"Sydney\",\"region\":\"New South Wales\",\"region_code\":\"NSW\",\"country\":\"AU\",\"country_name\":\"Australia\",\"country_code\":\"AU\",\"country_code_iso3\":\"AUS\",\"country_capital\":\"Canberra\",\"country_tld\":\".au\",\"continent_code\":\"OC\",\"in_eu\": false,\"postal\":\"2000\",\"latitude\": -33.859336,\"longitude\": 151.203624,\"timezone\":\"Australia/Sydney\",\"utc_offset\":\"+1000\",\"country_calling_code\":\"+61\",\"currency\":\"AUD\",\"currency_name\":\"Dollar\",\"languages\":\"en-AU\",\"country_area\": 7686850.0,\"country_population\": 24992369,\"asn\":\"AS13335\",\"org\":\"CLOUDFLARENET\",\"fields\": {\"Key A\":\"Value A\"}}";
        String sApiResponse = "{\"ip\":\"1.1.1.1\",\"network\":\"1.1.1.0/24\",\"version\":\"IPv4\",\"city\":\"Sydney\",\"region\":\"New South Wales\",\"region_code\":\"NSW\",\"country\":\"AU\",\"country_name\":\"Australia\",\"country_code\":\"AU\",\"country_code_iso3\":\"AUS\",\"country_capital\":\"Canberra\",\"country_tld\":\".au\",\"continent_code\":\"OC\",\"in_eu\": false,\"postal\":\"2000\",\"latitude\": -33.859336,\"longitude\": 151.203624,\"timezone\":\"Australia/Sydney\",\"utc_offset\":\"+1000\",\"country_calling_code\":\"+61\",\"currency\":\"AUD\",\"currency_name\":\"Dollar\",\"languages\":\"en-AU\",\"country_area\": 7686850.0,\"country_population\": 24992369,\"asn\":\"AS13335\",\"org\":\"CLOUDFLARENET\"}";
        JsonElement jApiResponse = JsonParser.parseString(sApiResponse);
        when(api.getAddressInformation("1.1.1.1")).thenReturn(jApiResponse);

        mvc.perform(post("/information/add?address=1.1.1.1&name=Key A&value=Value A"))
            .andExpect(status().isOk());

        mvc.perform(get("/information/get?address=1.1.1.1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(sExpectedResponse, false));
    }

    @Test
    void CustomAddressController_addDeleteGetInformationField() throws Exception {
        String sApiResponse = "{\"ip\":\"1.1.1.1\",\"network\":\"1.1.1.0/24\",\"version\":\"IPv4\",\"city\":\"Sydney\",\"region\":\"New South Wales\",\"region_code\":\"NSW\",\"country\":\"AU\",\"country_name\":\"Australia\",\"country_code\":\"AU\",\"country_code_iso3\":\"AUS\",\"country_capital\":\"Canberra\",\"country_tld\":\".au\",\"continent_code\":\"OC\",\"in_eu\": false,\"postal\":\"2000\",\"latitude\": -33.859336,\"longitude\": 151.203624,\"timezone\":\"Australia/Sydney\",\"utc_offset\":\"+1000\",\"country_calling_code\":\"+61\",\"currency\":\"AUD\",\"currency_name\":\"Dollar\",\"languages\":\"en-AU\",\"country_area\": 7686850.0,\"country_population\": 24992369,\"asn\":\"AS13335\",\"org\":\"CLOUDFLARENET\"}";
        JsonElement jApiResponse = JsonParser.parseString(sApiResponse);
        when(api.getAddressInformation("1.1.1.1")).thenReturn(jApiResponse);

        mvc.perform(post("/information/add?address=1.1.1.1&name=Key A&value=Value A"))
            .andExpect(status().isOk());
        
        mvc.perform(delete("/information/delete?address=1.1.1.1&name=Key A"))
            .andExpect(status().isOk());

        mvc.perform(get("/information/get?address=1.1.1.1"))
            .andExpect(status().isNotFound());
    }
}
