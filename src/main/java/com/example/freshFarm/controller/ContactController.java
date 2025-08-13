package com.example.freshFarm.controller;

import com.example.freshFarm.dto.ContactForm;
import com.example.freshFarm.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contact_us")
    public String supportGet(Model model) {
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "contact_us"; // templates/contact_us.html
    }

    @PostMapping("/contact_us")
    public String supportPost(@Valid @ModelAttribute("contactForm") ContactForm form,
                              BindingResult binding, RedirectAttributes ra) {
        if (binding.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", binding);
            ra.addFlashAttribute("contactForm", form);
            return "redirect:/contact_us";

        }
        Long id = contactService.save(form);
        ra.addFlashAttribute("contactSuccess", "Thanks! We’ll be in touch soon.");
        return "redirect:/contact_us";
    }

}

