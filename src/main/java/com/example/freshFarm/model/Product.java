package com.example.freshFarm.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data // Lombok for generating getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "unit_value", precision = 10, scale = 2)
    private BigDecimal unitValue;

    @Column(name = "unit_type", length = 20)
    private String unitType;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url", length = 255)
    private String imageUrl;
}
