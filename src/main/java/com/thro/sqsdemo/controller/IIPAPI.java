package com.thro.sqsdemo.controller;

import java.io.IOException;

import com.google.gson.JsonElement;

public interface IIPAPI {

    /**
     * Implementations of this method should fetch the remote API as JSON, parse the response and return the parsed result.
     * @param address Valid IP address as string
     * @return Parsed JSON. JSON will indicate an error if the IP address is invalid
     * @throws IOException API is not reachable
     */
    public JsonElement getAddressInformation(String address) throws IOException;
}
