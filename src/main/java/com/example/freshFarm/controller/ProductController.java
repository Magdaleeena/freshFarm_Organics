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

    @GetMapping("/fruits")
    public String fruits(Model model){
        List<Product> fruits = productRepository.findByCategoryIgnoreCase("fruit");
        model.addAttribute("fruits", fruits);
        return "/products/fruits";
    }

    @GetMapping("/vegetables")
    public String vegetables(Model model){
        List<Product> vegetables = productRepository.findByCategoryIgnoreCase("vegetable");
        model.addAttribute("vegetables", vegetables);
        return "products/vegetables";
    }

    @GetMapping("/milk")
    public String milk(Model model){
        List<Product> milk = productRepository.findByCategoryIgnoreCase("milk");
        model.addAttribute("milk", milk);
        return "products/milk";
    }

    @GetMapping("/cheese")
    public String cheese(Model model){
        List<Product> cheese = productRepository.findByCategoryIgnoreCase("cheese");
        model.addAttribute("cheese", cheese);
        return "products/cheese";
    }

    @GetMapping("/yoghurt")
    public String yoghurt(Model model){
        List<Product> yoghurt = productRepository.findByCategoryIgnoreCase("yoghurt");
        model.addAttribute("yoghurt", yoghurt);
        return "products/yoghurt";
    }

    @GetMapping("/boxes")
    public String boxes (Model model){
        List<Product> boxes = productRepository.findByCategoryIgnoreCase("packagebox");
        model.addAttribute("boxes", boxes);
        return "products/boxes";
    }
}
