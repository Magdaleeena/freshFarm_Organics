package com.example.freshFarm.service.impl;

import com.example.freshFarm.dto.ContactForm;
import com.example.freshFarm.model.ContactMessage;
import com.example.freshFarm.repository.ContactMessageRepository;
import com.example.freshFarm.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactMessageRepository repo;

    @Override
    public Long save(ContactForm f) {

        var saved = repo.save(ContactMessage.builder()
                .name(f.getName())
                .email(f.getEmail())
                .phone(f.getPhone())
                .subject(f.getSubject())
                .message(f.getMessage())
                .build());
        return saved.getId();
    }
}

