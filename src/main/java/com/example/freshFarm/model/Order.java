package com.example.freshFarm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_items")
    @JdbcTypeCode(SqlTypes.JSON)
    private String orderItems;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    public Order(String customerId, BigDecimal totalAmount) {
        this.customerId = customerId;
        this.orderDate = LocalDate.now();
        this.totalAmount = totalAmount;
        this.deliveryStatus = "pending";
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderItems() {
        return orderItems;
    }
}