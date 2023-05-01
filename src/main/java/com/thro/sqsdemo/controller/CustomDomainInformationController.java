package com.thro.sqsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.thro.sqsdemo.model.*;

@Controller
@RequestMapping(path="/domain")
public class CustomDomainInformationController {
    @Autowired
    private UserRepository database;

    @PostMapping(path="/add")
    @ResponseStatus(code = HttpStatus.OK)
    public void addDomain(@RequestParam String domain) {
        ExtendedDomainInformation domainObject = new ExtendedDomainInformation();
        domainObject.setDomain(domain);
        try {
            database.save(domainObject);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Domain already exists");
        }
    }

    @GetMapping(path="/get")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ExtendedDomainInformation> getDomainInformation(@RequestParam String domain) {
        var result = database.findByDomain(domain);
        if (result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Domain not found");
        
        var informationObject = result.get();
        return ResponseEntity.status(200).body(informationObject);
    }
}
