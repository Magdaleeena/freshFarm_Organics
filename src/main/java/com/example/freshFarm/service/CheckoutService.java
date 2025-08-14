package com.example.freshFarm.service;

import com.example.freshFarm.cart.Cart;
import com.example.freshFarm.cart.CartItem;
import com.example.freshFarm.model.Order;
import com.example.freshFarm.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutService {

    @Autowired
    private OrderRepository orderRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public Order createOrder(String customerId, Cart cart) {
        try {
            System.out.println("=== DEBUG: Starting createOrder ===");
            System.out.println("Customer ID: " + customerId);
            System.out.println("Cart: " + cart);
            System.out.println("Cart items: " + cart.getItems());
            System.out.println("Cart subtotal: " + cart.getSubtotal());

            // Create order with constructor
            Order order = new Order(customerId, cart.getSubtotal());
            System.out.println("Order created: " + order);

            // Convert cart items to JSON
            List<Map<String, Object>> orderItemsList = new ArrayList<>();
            for (Map.Entry<Long, CartItem> entry : cart.getItems().entrySet()) {
                CartItem item = entry.getValue();
                Map<String, Object> orderItem = new HashMap<>();
                orderItem.put("product_id", item.getProductId());
                orderItem.put("qty", item.getQty());
                orderItem.put("unit_price", item.getUnitPrice());
                orderItemsList.add(orderItem);
            }
            System.out.println("Order items list: " + orderItemsList);

            // Convert to JSON string
            ObjectMapper mapper = new ObjectMapper();
            try {
                String orderItemsJson = mapper.writeValueAsString(orderItemsList);
                order.setOrderItems(orderItemsJson);
                System.out.println("JSON created: " + orderItemsJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to create order JSON", e);
            }

            // Save to database
            System.out.println("About to save order to database...");
            Order savedOrder = orderRepository.save(order);
            System.out.println("Order saved successfully with ID: " + savedOrder.getOrderId());

            return savedOrder;

        } catch (Exception e) {
            System.err.println("=== ERROR in createOrder ===");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error type: " + e.getClass().getSimpleName());
            e.printStackTrace();
            throw e;
        }
    }

    public String createStripeCheckoutSession(Order order) {
        System.out.println("=== DEBUG: Starting createStripeCheckoutSession ===");
        System.out.println("Order: " + order);
        System.out.println("Stripe secret key: " + (stripeSecretKey != null ? "SET" : "NULL"));

        Stripe.apiKey = stripeSecretKey;

        try {
            System.out.println("Creating Stripe session params...");
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8080/checkout/success?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl("http://localhost:8080/checkout/cancel")
                    .addLineItem(SessionCreateParams.LineItem.builder()
                            .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("gbp")
                                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                            .setName("Order #" + order.getOrderId())
                                            .build())
                                    .setUnitAmount(order.getTotalAmount().multiply(BigDecimal.valueOf(100)).longValue())
                                    .build())
                            .setQuantity(1L)
                            .build())
                    .build();

            System.out.println("Stripe params created, calling Session.create...");
            Session session = Session.create(params);
            System.out.println("Stripe session created: " + session.getId());
            System.out.println("Stripe checkout URL: " + session.getUrl());

            return session.getUrl();

        } catch (StripeException e) {
            System.err.println("=== STRIPE ERROR ===");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error type: " + e.getClass().getSimpleName());
            e.printStackTrace();
            throw new RuntimeException("Failed to create checkout session", e);
        } catch (Exception e) {
            System.err.println("=== UNEXPECTED ERROR ===");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error type: " + e.getClass().getSimpleName());
            e.printStackTrace();
            throw e;
        }
    }

    public Order saveOrder(Order order) {
        System.out.println("=== DEBUG: Saving order to database ===");
        try {
            Order savedOrder = orderRepository.save(order);
            System.out.println("Order saved successfully with ID: " + savedOrder.getOrderId());
            return savedOrder;
        } catch (Exception e) {
            System.err.println("Error saving order: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void updateOrder(Order order) {
        System.out.println("=== DEBUG: Updating order in database ===");
        try {
            orderRepository.save(order);
            System.out.println("Order updated successfully");
        } catch (Exception e) {
            System.err.println("Error updating order: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
