package com.thro.sqsdemo.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.thro.sqsdemo.model.AddressEntry;
import com.thro.sqsdemo.model.AddressInformationRepo;


public class CustomAddressInformationControllerUnittest {
    
    @Mock
    private IIPAPI api;
    @Mock
    private AddressInformationRepo repo;

    @InjectMocks
    private CustomAddressInformationController service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void CustomAddressController_getExtendedAddressInformation_invalidAddress() throws Exception {
        assertThrows(ResponseStatusException.class, () -> service.getExtendedAddressInformation("1,1.1.1"));
    }

    @Test
    void CustomAddressController_getExtendedAddressInformation_noFields() throws Exception {
        when(repo.findAllByAddress("1.1.1.1")).thenReturn(new LinkedList<AddressEntry>());
        assertThrows(ResponseStatusException.class, () -> service.getExtendedAddressInformation("1.1.1.1"));
    }

    @Test
    void CustomAddressController_getExtendedAddressInformation_apiNotavailable() throws Exception {
        when(repo.findAllByAddress("1.1.1.1")).thenReturn(new LinkedList<AddressEntry>(Arrays.asList(new AddressEntry("1.1.1.1", "Key A", "Value A"))));
        when(api.getAddressInformation("1.1.1.1")).thenThrow(IOException.class);

        assertThrows(ResponseStatusException.class, () -> service.getExtendedAddressInformation("1.1.1.1"));
    }

    @Test
    void CustomAddressController_getExtendedAddressInformation_success() throws Exception {
        String sApiResponse = "{\r\n    \"ip\": \"1.1.1.1\",\r\n    \"network\": \"1.1.1.0/24\",\r\n    \"version\": \"IPv4\",\r\n    \"city\": \"Sydney\",\r\n    \"region\": \"New South Wales\",\r\n    \"region_code\": \"NSW\",\r\n    \"country\": \"AU\",\r\n    \"country_name\": \"Australia\",\r\n    \"country_code\": \"AU\",\r\n    \"country_code_iso3\": \"AUS\",\r\n    \"country_capital\": \"Canberra\",\r\n    \"country_tld\": \".au\",\r\n    \"continent_code\": \"OC\",\r\n    \"in_eu\": false,\r\n    \"postal\": \"2000\",\r\n    \"latitude\": -33.859336,\r\n    \"longitude\": 151.203624,\r\n    \"timezone\": \"Australia/Sydney\",\r\n    \"utc_offset\": \"+1000\",\r\n    \"country_calling_code\": \"+61\",\r\n    \"currency\": \"AUD\",\r\n    \"currency_name\": \"Dollar\",\r\n    \"languages\": \"en-AU\",\r\n    \"country_area\": 7686850.0,\r\n    \"country_population\": 24992369,\r\n    \"asn\": \"AS13335\",\r\n    \"org\": \"CLOUDFLARENET\"\r\n}";
        JsonElement jApiResponse = JsonParser.parseString(sApiResponse);

        when(repo.findAllByAddress("1.1.1.1")).thenReturn(new LinkedList<AddressEntry>(Arrays.asList(new AddressEntry("1.1.1.1", "Key A", "Value A"))));
        when(api.getAddressInformation("1.1.1.1")).thenReturn(jApiResponse);

        var result = service.getExtendedAddressInformation("1.1.1.1");
        assertEquals(HttpStatus.OK, result.getStatusCode());

        var responseJson = JsonParser.parseString(result.getBody()).getAsJsonObject();
        assertEquals("Value A", responseJson.get("fields").getAsJsonObject().get("Key A").getAsString());
        assertEquals("1.1.1.1", responseJson.get("ip").getAsString());
    }

    @Test
    void CustomAddressController_addInformationField_invalidAddress() throws Exception {
        assertThrows(ResponseStatusException.class, () -> service.addInformationField("256.0.0.1", "Key A", "Value A"));
    }

    @Test
    void CustomAddressController_addInformationField_success() throws Exception {
        when(repo.save(any(AddressEntry.class))).thenAnswer(call -> call.getArguments()[0]);
        assertDoesNotThrow(() -> service.addInformationField("1.1.1.1", "Key A", "Value A"));
        verify(repo, times(1)).save(any(AddressEntry.class));
    }

    @Test
    void CustomAddressController_deleteInformationField_invalidAddress() throws Exception {
        assertThrows(ResponseStatusException.class, () -> service.deleteInformationField("256.256.256.256", "Key A"));
    }

    @Test
    void CustomAddressController_deleteInformationField_success() throws Exception {
        doNothing().when(repo).deleteByAddressAndKey("1.1.1.1", "Key A");
        assertDoesNotThrow(() -> service.deleteInformationField("1.1.1.1", "Key A"));
        verify(repo, times(1)).deleteByAddressAndKey("1.1.1.1", "Key A");
    }
}
