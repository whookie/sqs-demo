package com.thro.sqsdemo.controller;
import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * Collection of preprocessing and validation methods 
 */
public class Validators {
    
    // Keep the class from being instantiated
    private Validators() {};

    /**
     * Strip all leading and trailing whitespaces off the address.
     * @param address Should, but must not be a valid IP address.
     * @return The address without trailing or leading whitespaces.
     */
    public static String preprocessAddress(String address) {
        return address.trim();
    }

    /**
     * Verify the provided IP address is a valid IPv4 address
     * @param address Should, but must not be a valid IP address.
     * @return True if the IPv4 address is valid, false otherwise.
     */
    public static boolean validateAddress(String address) {
        InetAddressValidator dv = InetAddressValidator.getInstance();
        return dv.isValidInet4Address(address);
    }
}
