package com.example.freshFarm.service.impl;

import com.example.freshFarm.model.Product;
import com.example.freshFarm.repository.ProductSearchRepository;
import com.example.freshFarm.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {
    private final ProductSearchRepository repo;

    @Override
    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll(pageable);
        }
        return repo.findByProductNameContainingIgnoreCase(keyword.trim(), pageable);
    }
}
