package com.example.freshFarm.controller;

import com.example.freshFarm.cart.Cart;
import com.example.freshFarm.model.Product;
import com.example.freshFarm.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("cart")
public class CartController {
    private final ProductRepository products;

    public CartController(ProductRepository products) {
        this.products = products;
    }

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int qty,
                            @ModelAttribute("cart") Cart cart) {
        Product product = products.findById(productId).orElseThrow();
        cart.add(product, qty);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/cart/update")
    public String update(@RequestParam Long productId,
                         @RequestParam int qty,
                         @ModelAttribute("cart") Cart cart) {
        cart.updateQty(productId, qty);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String remove(@RequestParam Long productId,
                         @ModelAttribute("cart") Cart cart) {
        cart.remove(productId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clear(@ModelAttribute("cart") Cart cart) {
        cart.clear();
        return "redirect:/cart";
    }
}
