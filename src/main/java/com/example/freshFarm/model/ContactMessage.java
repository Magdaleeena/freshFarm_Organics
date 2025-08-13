package com.example.freshFarm.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "contact_messages")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ContactMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)  private String name;
    @Column(nullable=false, length=255)  private String email;
    @Column(length=40)                   private String phone;
    @Column(nullable=false, length=200)  private String subject;

    @Column(nullable=false, length=5000)         private String message;

    @CreationTimestamp @Column(nullable=false, updatable=false)
    private Instant createdAt;
}

