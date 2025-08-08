package com.example.freshFarm.service.impl;


import com.example.freshFarm.model.Customer;
import com.example.freshFarm.repository.CustomerRepository;
import com.example.freshFarm.service.CustomerTransformer;
import com.example.freshFarm.service.DataLoaderService;
import com.example.freshFarm.util.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataLoaderServiceImpl implements DataLoaderService {

    private final CustomerRepository customerRepository;
    private final CustomerTransformer transformer;

    @Autowired
    public DataLoaderServiceImpl(CustomerRepository customerRepository, CustomerTransformer transformer) {
        this.customerRepository = customerRepository;
        this.transformer = transformer;
    }

    @Override
    public void loadAndTransform(String filePath) {
        List<Map<String, String>> rawData = CsvUtils.parseCsv(filePath);
        List<Customer> cleanedData = transformer.transform(rawData);
        List<Customer> uniqueCustomers = cleanedData.stream()
                .filter(c -> customerRepository.findByEmailAndPostcode(c.getEmail(), c.getPostcode()).isEmpty())
                .toList();

        customerRepository.saveAll(uniqueCustomers);

        System.out.println("✅ ETL completed: " + cleanedData.size() + " records saved.");
    }
}

