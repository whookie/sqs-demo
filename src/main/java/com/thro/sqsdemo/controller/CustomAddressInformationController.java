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

/**
 * Main REST controller for the custom information field API
 */
@Controller
@RequestMapping(path="/information")
public class CustomAddressInformationController {

    @Autowired
    private IIPAPI api;

    @Autowired
    private AddressInformationRepo repo;

    /**
     * REST-Endpoint used to get information regarding an IPv4 address.
     * @param address Hopefully valid IPv4
     * @return [200] with JSON content on success
     * @return [400] if the address is invalid
     * @return [404] if the address has no entry,
     * @return [503] if the API can't be reached
     */
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getExtendedAddressInformation(@RequestParam String address) {
        String addressTrim = Validators.preprocessText(address);
        if (! Validators.validateAddress(addressTrim))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Invalid");
        
        var result = repo.findAllByAddress(addressTrim);
        if (result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No fields for this address");
        
        JsonElement apiResponse;
        try {
            apiResponse = api.getAddressInformation(addressTrim);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Remote API not reachable");
        }

        AddressInformationBuilder builder = new AddressInformationBuilder(result);
        var addressEntries = builder.getAllAddressFields();
        Gson parser = new Gson();
        JsonElement customFields = parser.toJsonTree(addressEntries);
        apiResponse.getAsJsonObject().add("fields", customFields);
        String resultString = parser.toJson(apiResponse);

        return new ResponseEntity<>(resultString, HttpStatus.OK);
    }

    /**
     * Add a new custom field for an IPv4 address
     * @param address Hopefully valid IPv4 address
     * @param name Name (or Key) of the new field
     * @param value Value of the new field
     * @return [200] on success
     * @return [400] if the address is malformed
     */
    @PostMapping(path="/add")
    @ResponseStatus(code = HttpStatus.OK)
    public void addInformationField(@RequestParam String address, @RequestParam String name, @RequestParam String value) {
        String addressTrim = Validators.preprocessText(address);
        if (! Validators.validateAddress(addressTrim))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Malformed");
        String nameTrim = Validators.preprocessText(name);
        String valueTrim = Validators.preprocessText(value);

        AddressEntry info = new AddressEntry(address, nameTrim, valueTrim);
        repo.save(info);
    }

    /**
     * Delete an existing custom field by address and name
     * @param address Hopefully valid IPv4 address
     * @param name Name of the field
     * @return [200] on success
     * @return [400] if the address is malformed
     */
    @DeleteMapping(path="/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteInformationField(@RequestParam String address, @RequestParam String name) {
        String addressTrim = Validators.preprocessText(address);
        if (! Validators.validateAddress(addressTrim))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Malformed");
        String nameTrim = Validators.preprocessText(name);

        repo.deleteByAddressAndKey(addressTrim, nameTrim);
    }
}
