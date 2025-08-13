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

    @GetMapping("/support")
    public String supportGet(Model model) {
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "support"; // templates/support.html
    }

    @PostMapping("/support")
    public String supportPost(@Valid @ModelAttribute("contactForm") ContactForm form,
                              BindingResult binding, RedirectAttributes ra) {
        if (binding.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", binding);
            ra.addFlashAttribute("contactForm", form);
            return "redirect:/support";

        }
        Long id = contactService.save(form);
        ra.addFlashAttribute("contactSuccess", "Thanks! Your message has been received.");
        return "redirect:/support";
    }

}

