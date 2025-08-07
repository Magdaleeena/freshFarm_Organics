package com.example.freshFarm.repository;

import com.example.freshFarm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // We can come back here for custom queries
}
