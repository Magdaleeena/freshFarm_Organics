package com.example.freshFarm.service;


import com.example.freshFarm.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface ProductSearchService {
    Page<Product> searchProducts(String keyword, Pageable pageable);
}
