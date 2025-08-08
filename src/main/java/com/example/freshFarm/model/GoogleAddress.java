package com.example.freshFarm.model;

import com.google.gson.JsonObject;

public class GoogleAddress {
    public GoogleAddress() {
    }

    private String address;

    public String getAddress_url() {
        return address;
    }

    public JsonObject setAddress_url(String address, String postCode) {
        JsonObject json = new JsonObject();
        JsonObject array = new JsonObject();
        array.addProperty("revision", 0);
        array.addProperty("regionCode", "GB");
        array.addProperty("addressLines", address);
        array.addProperty("postalCode", postCode);
        json.add("address", array);
        System.out.println("json array sent:");
        System.out.println(json);
        this.address = String.valueOf(array);
        return json;
    }
}

// {"address": {"postalCode": "DL3 6TD", "addressLines" : ["5 Westbrook"]}