package com.example.freshFarm.controller;

import com.example.freshFarm.model.AddressResponse;
import com.example.freshFarm.model.Customer;
import com.example.freshFarm.model.GoogleAddress;
import com.example.freshFarm.repository.CustomerRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@RestController
public class AddressController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/AddressChecker")
    public ModelAndView addressChecker() throws URISyntaxException, IOException, InterruptedException {
        ModelAndView addressChecker = new ModelAndView("/Address/AddressChecker");

        GoogleAddress googleAddress = new GoogleAddress();
        // 00290530-22f9-4929-ae5d-3f2802abf327

        String user_id = "00290530-22f9-4929-ae5d-3f2802abf327";
        Optional<Customer> customerLookup = customerRepository.findById(user_id);
        System.out.println(customerLookup);

        String address_line1 = "";
        String postcode = "";
        JsonObject queryObject = googleAddress.setAddress_url(address_line1, postcode);
        Gson gson = new Gson();
        System.out.println("Pre-toJson: " + googleAddress);
        String jsonRequest = gson.toJson(queryObject);
        System.out.println(jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://addressvalidation.googleapis.com/v1:validateAddress?key="))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());

        addressChecker.addObject("postResponse", postResponse.body());
        return addressChecker;
    }

    @PostMapping
    public String sendAddress() throws URISyntaxException, IOException, InterruptedException {
        GoogleAddress googleAddress = new GoogleAddress();
        JsonObject queryObject = googleAddress.setAddress_url("", "");
        Gson gson = new Gson();
        System.out.println("Pre-toJson: " + googleAddress);
        String jsonRequest = gson.toJson(queryObject);
        System.out.println(jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://addressvalidation.googleapis.com/v1:validateAddress?key="))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());
        return postResponse.body();
    }
}
