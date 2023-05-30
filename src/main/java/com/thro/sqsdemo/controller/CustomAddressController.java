package com.thro.sqsdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.thro.sqsdemo.model.*;

@Controller
@RequestMapping(path="/address")
public class CustomAddressController {
    @Autowired
    private AddressRepository database;

    @PostMapping(path="/add")
    @ResponseStatus(code = HttpStatus.OK)
    public void addAddress(@RequestParam String address) {
        String address_trimmed = Validators.preprocessAddress(address);
        if (! Validators.validateAddress(address_trimmed))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Malformed");

        ExtendedAddressInformation addressObject = new ExtendedAddressInformation();
        addressObject.setAddress(address_trimmed);
        try {
            database.save(addressObject);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Address already exists");
        }
    }

    @GetMapping(path="/get")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<JsonElement> getAddressInformation(@RequestParam String address) {
        String address_trimmed = Validators.preprocessAddress(address);
        if (! Validators.validateAddress(address_trimmed))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Malformed");

        JsonElement api_result;
        try {
            api_result = IPAPI.getAddressInformation(address_trimmed);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Remote API may be down to refusing our connection");
        }

        if (api_result.getAsJsonObject().get("error").getAsBoolean()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, String.format("Received error response: %s", api_result.getAsJsonObject().get("reason").getAsString()));
        }

        var result = database.findByAddress(address_trimmed);
        if (result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");

        var informationObject = result.get();
        JsonElement informationObjectJson = new GsonBuilder().create().toJsonTree(informationObject, ExtendedAddressInformation.class);
        api_result.getAsJsonObject().add("additions", informationObjectJson);
        return ResponseEntity.status(200).body(api_result);
    }
}
