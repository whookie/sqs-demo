package com.thro.sqsdemo.controller;
import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * Collection of preprocessing and validation methods 
 */
public class Validators {
    
    // Keep the class from being instantiated
    private Validators() {};

    /**
     * Strip all leading and trailing whitespaces off a text
     * @param text Text to be stripped
     * @return The text without leading or trailing whitespaces
     */
    public static String preprocessText(String text) {
        return text.trim();
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
