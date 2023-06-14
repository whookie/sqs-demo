package com.thro.sqsdemo.controller;
import org.apache.commons.validator.routines.InetAddressValidator;

public class Validators {
    
    // Keep the class from being instantiated
    private Validators() {};

    public static String preprocessAddress(String address) {
        return address.trim();
    }

    public static boolean validateAddress(String address) {
        InetAddressValidator dv = InetAddressValidator.getInstance();
        return dv.isValidInet4Address(address);
    }
}
