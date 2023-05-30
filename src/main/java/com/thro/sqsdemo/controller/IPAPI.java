package com.thro.sqsdemo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class IPAPI {
    private IPAPI() {}

    public static JsonElement getAddressInformation(String address) throws IOException {
        // At this point, assume the domain is already validated
        String ipapi_domain = String.format("https://ipapi.co/%s/json", address);
        URLConnection request = new URL(ipapi_domain).openConnection();
        request.connect();
        InputStreamReader reader = new InputStreamReader((InputStream)request.getContent());
        return JsonParser.parseReader(reader);
    }
}
