package com.example.freshFarm.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
public class ProfileForm {
    @NotBlank
    @Size(max=120)  private String forename;
    @Size(max=120)  private String surname;
    @NotBlank @Email
    @Size(max=255) private String email;
    @Pattern(regexp="^[0-9+()\\-\\s]*$", message="Invalid phone number")
    @Size(max=40)              private String phone_number;
    @NotBlank @Size(max=200)   private String address_line1;
    @NotBlank @Size(max=120)  private String town_city;
    @NotBlank @Size(max=10) private String postcode;
    @NotBlank @Size(max=120) private String country;
}
