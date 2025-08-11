package com.example.freshFarm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class ProfileController {

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
}
