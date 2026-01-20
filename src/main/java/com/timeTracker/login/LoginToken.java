package com.timeTracker.login;

import java.time.Instant;

import com.timeTracker.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;

@Column(nullable = false)
private Instant expiredAt;

private Instant usedAt;

@Column(nullable = false)
private Instant createdAt = Instant.now();

protected LoginToken() {}

public LoginToken(String token, User user, Instant expiredAt) {
    this.token = token;
    this.user = user;
    this.expiredAt = expiredAt;
    this.createdAt = Instant.now();
}
}
