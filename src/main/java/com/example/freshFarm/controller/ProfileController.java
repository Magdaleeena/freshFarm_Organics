package com.example.freshFarm.controller;

import com.example.freshFarm.model.Customer;
import com.example.freshFarm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/profile")
    public ModelAndView profile(){
        ModelAndView profileView = new ModelAndView("/customer/profile");

        DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String email = (String) principal.getAttributes().get("email");
        Optional<Customer> user = customerRepository.findUserByEmail(email);

            String profileForename = user.get().getForename();
            String profileSurname = user.get().getSurname();
            String addressLine1 = user.get().getAddressLine1();
            String addressTown = user.get().getTownCity();
            String addressPostcode = user.get().getPostcode();

            profileView.addObject("profile_name", profileForename);
            profileView.addObject("profile_surname", profileSurname);
            profileView.addObject("addressLine1", addressLine1);
            profileView.addObject("addressTown",addressTown);
            profileView.addObject("addressPostcode",addressPostcode);

        return profileView;
    }

    @PostMapping("/profile")
    public RedirectView update(@ModelAttribute Customer customer) {
        DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String email = (String) principal.getAttributes().get("email");
        Optional<Customer> oldUser = customerRepository.findUserByEmail(email);

        String newAddressLine1 = customer.getAddressLine1();
        String newAddressTown = customer.getTownCity();
        String newPostcode = customer.getPostcode();

        System.out.println(newAddressLine1);
        System.out.println(newAddressTown);
        System.out.println(newPostcode);

        oldUser.get().setAddressLine1(newAddressLine1);
        oldUser.get().setTownCity(newAddressTown);
        oldUser.get().setPostcode(newPostcode);

        customerRepository.save(oldUser.get());

        return new RedirectView("/profile");
    }
}
