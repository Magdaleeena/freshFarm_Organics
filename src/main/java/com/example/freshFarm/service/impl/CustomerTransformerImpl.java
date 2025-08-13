package com.example.freshFarm.service.impl;

import com.example.freshFarm.model.Customer;
import com.example.freshFarm.service.CustomerTransformer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerTransformerImpl implements CustomerTransformer {

    private static final Set<String> BLOCKED_TITLES = Set.of("mr", "ms", "mrs", "miss", "dr");

    @Override
    public List<Customer> transform(List<Map<String, String>> rawData) {
        return rawData.stream()
                .filter(this::hasAllRequiredFields)
                .filter(this::isCleanTitle)
                .map(this::mapToCustomer)
                .filter(this::isValidPhone)
                .filter(c -> !c.getCountry().equalsIgnoreCase("UNITED KINGDOM"))
                .collect(Collectors.toList());
    }

    private boolean hasAllRequiredFields(Map<String, String> row) {
        return Stream.of("customer_name", "AddressLine1", "Town/City", "Country", "Postcode", "email", "phone_number")
                .allMatch(field -> StringUtils.hasText(row.get(field)));
    }

    private boolean isCleanTitle(Map<String, String> row) {
        String name = row.get("customer_name").toLowerCase();
        return BLOCKED_TITLES.stream().noneMatch(name::startsWith);
    }

    private boolean isValidPhone(Customer customer) {
        return customer.getPhoneNumber().startsWith("+44");
    }

    private Customer mapToCustomer(Map<String, String> row) {
        String[] nameParts = row.get("customer_name").trim().split("\\s+", 2);
        String forename = nameParts.length > 0 ? nameParts[0] : "";
        String surname = nameParts.length > 1 ? nameParts[1] : "";

        Customer customer = new Customer();
        customer.setCustomerId(UUID.randomUUID().toString());
        customer.setForename(forename);
        customer.setSurname(surname);
        customer.setAddressLine1(row.get("AddressLine1"));
        customer.setTownCity(row.get("Town/City"));
        customer.setCountry(row.get("Country"));
        customer.setPostcode(row.get("Postcode"));
        customer.setEmail(row.get("email"));
        customer.setPhoneNumber(row.get("phone_number"));

        return customer;
    }
}


