package com.thro.sqsdemo.controller;
import org.apache.commons.validator.routines.InetAddressValidator;

public class Validators {
    private Validators() {};

    public static String preprocessAddress(String address) {
        // 1. Trim whitespaces
        // 2. Validate URL format
        String domain_trimmed = address.trim();
        return domain_trimmed;
    }

    public static boolean validateAddress(String address) {
        InetAddressValidator dv = InetAddressValidator.getInstance();
        return dv.isValidInet4Address(address);
    }
}
