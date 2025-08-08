package com.example.freshFarm.service;

import com.example.freshFarm.model.Customer;
import java.util.List;
import java.util.Map;

public interface CustomerTransformer {
    List<Customer> transform(List<Map<String, String>> rawData);
}
