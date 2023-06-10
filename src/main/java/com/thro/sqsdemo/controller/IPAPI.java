package com.thro.sqsdemo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class IPAPI {
    private final String base_url = "https://ipapi.co/%s/json";
    private URLConnection connection;
    
    public IPAPI(String address) throws IOException {
        // Assume the address is already validated - and even if not, the API will notify us
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String ipapi_domain = String.format(base_url, encodedAddress);
        connection = new URL(ipapi_domain).openConnection();
    }

    public JsonElement getAddressInformation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return JsonParser.parseReader(reader);
    }
}
