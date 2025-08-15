package com.example.freshFarm.controller;

import com.example.freshFarm.model.Product;
import com.example.freshFarm.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final ProductSearchService service;

    @GetMapping("/search")
    public String search(@RequestParam(name = "q", required = false) String keyword,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> result = Page.empty(pageable);
        if (keyword != null && !keyword.trim().isEmpty()){
            result = service.searchProducts(keyword.trim(), pageable);
        }
//        Page<Product> results = service.searchProducts(keyword, PageRequest.of(page, size));
        model.addAttribute("results", result);
        model.addAttribute("q", keyword);
        return "search";  // templates/search.html
    }
}
