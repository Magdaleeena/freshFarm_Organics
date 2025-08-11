package com.example.freshFarm.service;

import com.example.freshFarm.model.Product;
import com.example.freshFarm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok to generate constructor
public class ProductService {
    private final ProductRepository productRepository;

    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}