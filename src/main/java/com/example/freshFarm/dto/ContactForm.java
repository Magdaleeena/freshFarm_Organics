package com.example.freshFarm.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContactForm {
    @NotBlank @Size(max=120)  private String name;
    @NotBlank @Email @Size(max=255) private String email;
    @Pattern(regexp="^[0-9+()\\-\\s]*$", message="Invalid phone number")
    @Size(max=40)              private String phone;
    @NotBlank @Size(max=200)   private String subject;
    @NotBlank @Size(min=10, max=5000) private String message;
}

