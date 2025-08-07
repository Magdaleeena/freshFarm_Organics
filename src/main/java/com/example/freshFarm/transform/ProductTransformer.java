package com.example.freshFarm.transform;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.*;

public class ProductTransformer {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> products = mapper.readValue(
                new File("freshFarm_products.json"),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        List<Map<String, Object>> dbProducts = new ArrayList<>();

        for (Map<String, Object> product : products) {
            Map<String, Object> dbProduct = new LinkedHashMap<>();

            // Map and transform fields
            dbProduct.put("product_name", product.get("productName"));

            // Transform unitPrice: "£1.16" -> 1.16 (as Double)
            String priceStr = (String) product.get("unitPrice");
            if (priceStr != null && priceStr.startsWith("£")) {
                dbProduct.put("unit_price", Double.parseDouble(priceStr.replace("£", "")));
            } else {
                dbProduct.put("unit_price", 0.0);
            }

            // Set unit_type and unit_value based on category
            String category = (String) product.get("category");
            String unitType = "unit";
            double unitValue = 1.0;
            if (category != null) {
                String cat = category.trim().toLowerCase();
                if (cat.contains("fruit")) {
                    unitType = "kg";
                    unitValue = 0.2;
                } else if (cat.contains("vegetable")) {
                    unitType = "kg";
                    unitValue = 0.25;
                } else if (cat.contains("box")) {
                    unitType = "box";
                    unitValue = 3.0;
                }
                dbProduct.put("category", cat);
            } else {
                dbProduct.put("category", "unknown");
            }
            dbProduct.put("unit_type", unitType);
            dbProduct.put("unit_value", unitValue);

            // Set description (default or blank)
            dbProduct.put("description", "Fresh produce");

            // Map imageUrl
            dbProduct.put("image_url", product.get("imageUrl"));

            dbProducts.add(dbProduct);
        }

        // Write transformed data to new JSON file
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File("freshFarm_products_db.json"), dbProducts);

        System.out.println("Transformation complete! See freshFarm_products_db.json");
    }
}