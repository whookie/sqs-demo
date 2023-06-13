package com.thro.sqsdemo.controller;

import java.io.IOException;

import com.google.gson.JsonElement;


public interface IIPAPI {
    public JsonElement getAddressInformation(String address) throws IOException;
}
