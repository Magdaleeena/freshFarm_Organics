package com.example.freshFarm.service.impl;

import com.example.freshFarm.dto.ContactForm;
import com.example.freshFarm.dto.ProfileForm;
import com.example.freshFarm.model.ContactMessage;
import com.example.freshFarm.model.Customer;
import com.example.freshFarm.repository.ContactMessageRepository;
import com.example.freshFarm.repository.CustomerRepository;
import com.example.freshFarm.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final CustomerRepository repo;

    @Override
    public String save(ProfileForm f) {

        var saved = repo.save(Customer.builder()
                .forename(f.getForename())
                .surname(f.getSurname())
                .email(f.getEmail())
                .phoneNumber(f.getPhone_number())
                .addressLine1(f.getAddress_line1())
                .postcode(f.getPostcode())
                .country(f.getCountry())
                .townCity(f.getTown_city())
                .build());
        return saved.getCustomerId();
    }
}