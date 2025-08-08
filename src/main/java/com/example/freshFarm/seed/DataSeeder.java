package com.example.freshFarm.seed;

import com.example.freshFarm.service.DataLoaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("seed")
public class DataSeeder implements CommandLineRunner {
    private final DataLoaderService dataLoaderService;

    public DataSeeder(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    @Override
    public void run(String... args) {
        String filePath = "src/main/resources/data/customer_final.csv";
        dataLoaderService.loadAndTransform(filePath);
    }
}
