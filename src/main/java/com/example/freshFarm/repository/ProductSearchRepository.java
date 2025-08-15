package com.example.freshFarm.repository;

import com.example.freshFarm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearchRepository extends JpaRepository<Product,Long> {
    Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);
}
