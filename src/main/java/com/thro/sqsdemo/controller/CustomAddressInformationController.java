package com.thro.sqsdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.thro.sqsdemo.model.*;

@Controller
@RequestMapping(path="/information")
public class CustomAddressInformationController {

    @Autowired
    private IIPAPI api;

    @Autowired
    private AddressInformationRepo repo;

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getExtendedAddressInformation(@RequestParam String address) {
        // First, get the entries.
        // Second, gather information from IPAPI
        // Third, add the fields to the response and return

        String address_trim = Validators.preprocessAddress(address);
        if (! Validators.validateAddress(address_trim))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Invalid");
        
        var result = repo.findAllByAddress(address_trim);
        if (result.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No fields for this address");
        
        JsonElement apiResponse;
        try {
            apiResponse = api.getAddressInformation(address_trim);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Remote API not reachable");
        }

        AddressInformationBuilder builder = new AddressInformationBuilder(result);
        var addressEntries = builder.getAllAddressFields();
        Gson parser = new Gson();
        JsonElement customFields = parser.toJsonTree(addressEntries);
        apiResponse.getAsJsonObject().add("fields", customFields);
        String resultString = parser.toJson(apiResponse);

        return new ResponseEntity<String>(resultString, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    @ResponseStatus(code = HttpStatus.OK)
    public void addInformationField(@RequestParam String address, @RequestParam String name, @RequestParam String value) {
        String address_trim = Validators.preprocessAddress(address);
        if (! Validators.validateAddress(address_trim))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Malformed");

        AddressEntry info = new AddressEntry(address, name, value);
        repo.save(info);
    }

    @DeleteMapping(path="/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteInformationField(@RequestParam String address, @RequestParam String name) {
        String address_trim = Validators.preprocessAddress(address);
        if (! Validators.validateAddress(address_trim))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Malformed");

        repo.deleteByAddressAndKey(address_trim, name);
    }
}
