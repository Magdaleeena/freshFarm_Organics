package com.example.freshFarm.controller;

import com.example.freshFarm.model.Customer;
import com.example.freshFarm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer-form")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer_form";
    }

    @PostMapping("/submit-customer")
    public String submitCustomer(@ModelAttribute("customer") Customer customer) {
        customer.setCustomerId(UUID.randomUUID().toString());
        customerRepository.save(customer);
        return "redirect:/customer-form?success";
    }
}

