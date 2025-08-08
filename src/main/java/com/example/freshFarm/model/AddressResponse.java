package com.example.freshFarm.model;

import com.google.gson.JsonObject;
import org.openqa.selenium.json.Json;

public class AddressResponse {
    public void AddressResponse(){

    }

    private JsonObject formattedAddress;

    public JsonObject getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(JsonObject formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
