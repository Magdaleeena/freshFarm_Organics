package com.example.freshFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.freshFarm.model.Product;
import com.example.freshFarm.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/profile")
    public ModelAndView profile(){
        ModelAndView profileView = new ModelAndView("/profile");

//        DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();

//        String username = (String) principal.getAttributes().get("email");
//        Optional<User> user = userRepository.findUserByUsername(username);

//            String profileName = user.get().getName();
//            profileView.addObject("profile_name", profileName);
//            String userImage = user.get().getUserImage();
//            profileView.addObject("user_image", userImage);


        return profileView;
    }
    @GetMapping("/vegetables")
    public ModelAndView vegetables(){
        List<Product> vegetables = productRepository.findByCategory("vegetable");
        ModelAndView vegView = new ModelAndView("vegetables");
        vegView.addObject("vegetables", vegetables);
        return vegView;
    }
}
