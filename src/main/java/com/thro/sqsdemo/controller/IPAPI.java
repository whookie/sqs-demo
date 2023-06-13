package com.thro.sqsdemo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


@Service
public class IPAPI implements IIPAPI {
    private static final String apiUrlTemplate = "https://ipapi.co/%s/json";

    public JsonElement getAddressInformation(String address) throws IOException {
        // Assume the address is already validated - and even if not, the API will notify us
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String ipapi_domain = String.format(apiUrlTemplate, encodedAddress);
        URLConnection connection = new URL(ipapi_domain).openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return JsonParser.parseReader(reader);
    }
}
