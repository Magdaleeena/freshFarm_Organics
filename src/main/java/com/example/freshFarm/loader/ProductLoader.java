package com.example.freshFarm.loader;

import com.example.freshFarm.model.Product;
import com.example.freshFarm.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Profile("seed")
@Component
@RequiredArgsConstructor
public class ProductLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        try {
            File jsonFile = new File("freshFarm_products_db.json");
            if (!jsonFile.exists()) {
                System.out.println("JSON file not found: " + jsonFile.getAbsolutePath());
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> products = mapper.readValue(
                    jsonFile,
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            List<Product> productEntities = products.stream().map(p -> Product.builder()
                    .productName((String) p.get("product_name"))
                    .unitPrice(new BigDecimal(p.get("unit_price").toString()))
                    .unitValue(new BigDecimal(p.get("unit_value").toString()))
                    .unitType((String) p.get("unit_type"))
                    .category((String) p.get("category"))
                    .description((String) p.get("description"))
                    .imageUrl((String) p.get("image_url"))
                    .build()
            ).collect(Collectors.toList());

            int successCount = 0;
            for (Product product : productEntities) {
                try {
                    if (product.getProductName() == null || product.getUnitPrice() == null) {
                        System.out.println("Skipping product with missing required fields: " + product);
                        continue;
                    }
                    productRepository.save(product);
                    successCount++;
                } catch (Exception e) {
                    System.out.println("Failed to save product: " + product.getProductName());
                    e.printStackTrace();
                }
            }
            System.out.println("Successfully loaded " + successCount + " products into the database.");
        } catch (Exception e) {
            System.out.println("Error loading products: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
