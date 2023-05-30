package com.thro.sqsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import com.thro.sqsdemo.model.*;

@Controller
@RequestMapping(path="/information")
public class CustomAddressInformationController {
    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private InformationRepository informationRepo;


    @PostMapping(path="/add")
    @ResponseStatus(code = HttpStatus.OK)
    public void addInformationField(@RequestParam String address, @RequestParam String name, @RequestParam String value) {
        String address_trimmed = Validators.preprocessAddress(address);
        if (! Validators.validateAddress(address_trimmed))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Malformed");

        var result = addressRepo.findByAddress(address_trimmed);
        if (result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This address has not been added");

        CustomInformationField field = new CustomInformationField();
        field.setName(name);
        field.setValue(value);
        field.setParentObject(result.get());

        try {
            informationRepo.save(field);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Field already exists");
        }
    }

    @DeleteMapping(path="/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteInformationField(@RequestParam String address, @RequestParam String name) {
        String address_trimmed = Validators.preprocessAddress(address);
        if (! Validators.validateAddress(address_trimmed))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address Malformed");

        var result = addressRepo.findByAddress(address_trimmed);
        if (result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This address has not been added");

        CustomInformationField field = new CustomInformationField();
        field.setName(name);
        field.setParentObject(result.get());

        informationRepo.delete(field);
    }
}
