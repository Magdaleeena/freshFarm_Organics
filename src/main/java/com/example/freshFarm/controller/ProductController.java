package com.example.freshFarm.controller;

import org.springframework.ui.Model;
import com.example.freshFarm.model.Product;
import com.example.freshFarm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/milk")
    public String milk(Model model){
        List<Product> milk = productRepository.findByCategoryIgnoreCase("milk");
        model.addAttribute("milk", milk);
        return "products/milk";
    }
}
