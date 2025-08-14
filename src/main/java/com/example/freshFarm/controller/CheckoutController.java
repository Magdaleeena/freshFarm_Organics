package com.example.freshFarm.controller;

import com.example.freshFarm.cart.Cart;
import com.example.freshFarm.model.Customer;
import com.example.freshFarm.model.Order;
import com.example.freshFarm.repository.CustomerRepository;
import com.example.freshFarm.service.CheckoutService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/checkout")
    public String checkoutPage(Model model, HttpSession session) {
        // Get cart from session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        // TEMPORARY: Get test customer for development
        Customer customer = getTestCustomer();
        if (customer == null) {
            // No test customer found, redirect to cart with error
            return "redirect:/cart?error=no-customer";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("customer", customer);
        return "checkout";
    }

    @PostMapping("/checkout/create-session")
    public String createCheckoutSession(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Get cart from session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        // TEMPORARY: Get test customer for development
        Customer customer = getTestCustomer();
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "No customer found for testing");
            return "redirect:/cart";
        }

        try {
            // Create order with real customer ID
            Order order = checkoutService.createOrder(customer.getCustomerId(), cart);

            // Store order temporarily in session
            session.setAttribute("pendingOrder", order);

            // Create Stripe checkout session
            String checkoutUrl = checkoutService.createStripeCheckoutSession(order);

            // Redirect to Stripe checkout
            return "redirect:" + checkoutUrl;

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to create checkout session: " + e.getMessage());
            return "redirect:/cart";
        }
    }

    // TEMPORARY METHOD: Get a test customer for development
    private Customer getTestCustomer() {
        try {
            // Try to get the first customer from CSV data
            // will be changed to get a specific customer by email or ID
            return customerRepository.findAll().get(0);
        } catch (Exception e) {
            System.err.println("Error getting test customer: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/checkout/success")
    public String checkoutSuccess(@RequestParam String session_id,
                                  HttpSession session,
                                  Model model) {
        try {
            // Get the pending order from session
            Order order = (Order) session.getAttribute("pendingOrder");
            if (order == null) {
                return "redirect:/cart";
            }

            // Payment was successful, now save order to database
            Order savedOrder = checkoutService.saveOrder(order);

            // Update order status to confirmed
            savedOrder.setDeliveryStatus("confirmed");
            checkoutService.updateOrder(savedOrder);

            // Clear cart and pending order from session
            session.removeAttribute("cart");
            session.removeAttribute("pendingOrder");

            // Add order to model for success page
            model.addAttribute("order", savedOrder);
            model.addAttribute("stripeSessionId", session_id);

            return "checkout-success";

        } catch (Exception e) {
            model.addAttribute("error", "Failed to process successful payment: " + e.getMessage());
            return "checkout";
        }
    }

    @GetMapping("/checkout/cancel")
    public String checkoutCancel(HttpSession session, Model model) {
        // User cancelled payment
        session.removeAttribute("pendingOrder");

        model.addAttribute("message", "Payment was cancelled. Your cart is still available.");
        return "redirect:/cart";
    }
}