package com.thro.sqsdemo.model;

import java.io.Serializable;

public class AddressEntryId implements Serializable {
    // This class is solely used as key definition for the AddressEntry entity.
    // It requires the address and key fields, but does not require other methods, getter or setter.

    @SuppressWarnings("unused")
    private String address; // NOSONAR

    @SuppressWarnings("unused")
    private String key;     // NOSONAR
}
