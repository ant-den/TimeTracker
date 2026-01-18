package com.timeTracker.login;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "login_tokens")
@Getter
@Setter
public class LoginToken {
    
@Id
private String token;

private UUID userId;

@Column(nullable = false)
private Instant expiredAt;

private Instant usedAt;

protected LoginToken() {}

public LoginToken(String token, UUID userId, Instant expiredAt) {
    this.token = token;
    this.userId = userId;
    this.expiredAt = expiredAt;
}
}
