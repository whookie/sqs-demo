package com.thro.sqsdemo.controller;
import org.apache.commons.validator.routines.DomainValidator;

public class Validators {
    private Validators() {};

    public static String preprocessDomain(String domain) {
        // 1. Trim whitespaces
        // 2. Validate URL format

        String domain_trimmed = domain.trim();

        return domain_trimmed;
    }

    public static boolean validateDomain(String domain) {
        DomainValidator dv = DomainValidator.getInstance();
        return dv.isValid(domain);
    }
}
