package com.example.freshFarm.controller;

import org.springframework.ui.Model;
import com.example.freshFarm.model.Product;
import com.example.freshFarm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/fruits")
    public String fruits(Model model){
        List<Product> fruits = productRepository.findByCategoryIgnoreCase("fruit");
        model.addAttribute("fruits", fruits);
        return "fruits";
    }

    @GetMapping("/vegetables")
    public ModelAndView vegetables(){
        List<Product> vegetables = productRepository.findByCategoryIgnoreCase("vegetable");
        ModelAndView vegView = new ModelAndView("vegetables");
        vegView.addObject("vegetables", vegetables);
        return vegView;
    }

    @GetMapping("/milk")
    public String milk(Model model){
        List<Product> milk = productRepository.findByCategoryIgnoreCase("milk");
        model.addAttribute("milk", milk);
        return "products/milk";
    }
}
