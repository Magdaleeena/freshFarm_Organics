package com.example.freshFarm.controller;

import com.example.freshFarm.dto.ProfileForm;
import com.example.freshFarm.model.Customer;
import com.example.freshFarm.model.Order;
import com.example.freshFarm.model.Product;
import com.example.freshFarm.repository.CustomerRepository;
import com.example.freshFarm.repository.OrderRepository;
import com.example.freshFarm.repository.ProductRepository;
import com.example.freshFarm.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProfileService profileService;

    @GetMapping("/profile")
    public ModelAndView profile(){

        DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String email = (String) principal.getAttributes().get("email");
        Optional<Customer> user = customerRepository.findUserByEmail(email);

            ModelAndView profileView = new ModelAndView("/customer/profile");

            String profileForename = user.get().getForename();
            String profileSurname = user.get().getSurname();
            String addressLine1 = user.get().getAddressLine1();
            String addressTown = user.get().getTownCity();
            String addressPostcode = user.get().getPostcode();
            String customer_id = user.get().getCustomerId();

            profileView.addObject("profile_name", profileForename);
            profileView.addObject("profile_surname", profileSurname);
            profileView.addObject("addressLine1", addressLine1);
            profileView.addObject("addressTown", addressTown);
            profileView.addObject("addressPostcode", addressPostcode);

            List<Order> orders = orderRepository.findByCustomerId(customer_id);
            profileView.addObject("orders", orders);

            return profileView;
    }

    @PostMapping("/newprofile")
    public RedirectView newProfile(@Valid @ModelAttribute("contactForm") ProfileForm form,
                                   BindingResult binding, RedirectAttributes ra){
        if (binding.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", binding);
            ra.addFlashAttribute("contactForm", form);
            return new RedirectView("/");

        }

        //        Long id = profileService.save(form);

        return new RedirectView("/profile");
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

        oldUser.get().setAddressLine1(newAddressLine1);
        oldUser.get().setTownCity(newAddressTown);
        oldUser.get().setPostcode(newPostcode);

        customerRepository.save(oldUser.get());

        return new RedirectView("/profile");
    }

    @GetMapping("/orders/{id}")
    public ModelAndView userPage(@PathVariable Long id){
        Optional<Order> order = orderRepository.findById(id);

        ModelAndView orderView = new ModelAndView("customer/orders");

        String orderItems = order.get().getOrderItems();

        Optional<Product> product = productRepository.findById(Long.valueOf(22));

        orderView.addObject("product", product.get());

        return orderView;
    }
}
