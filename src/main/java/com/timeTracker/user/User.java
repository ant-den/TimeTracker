package com.timeTracker.user;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 320)
    private String email;

    @Column(name = "is_paid", nullable = false)
    private boolean paid;

    private String stripeCustomerId;

    private Instant paidAt;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    protected User() {
    }

    public User(UUID id, String email) {
        this.id = id;
        this.email = email.toLowerCase().trim();
        this.paid = false;
        this.createdAt = Instant.now();

    }
}
