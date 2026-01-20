package com.timeTracker.auth;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.timeTracker.login.LoginToken;
import com.timeTracker.login.LoginTokenRepository;
import com.timeTracker.user.User;
import com.timeTracker.user.UserRepository;


@Service
public class MagicLinkService {
    private final LoginTokenRepository tokenRepo;
    private final UserRepository userRepo;
    private final SecureRandom random = new SecureRandom();

    @Value("${app.base-url}")
    private String baseUrl;

    public MagicLinkService(UserRepository userRepo, LoginTokenRepository tokenRepo) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }
    public String createMagicLink(String email) {
    String normalized = email.toLowerCase().trim();
    User user = userRepo.findByEmail(normalized).orElse(null);
    if (user == null) {
        user = userRepo.save(new User(UUID.randomUUID(), normalized));
    }

    String token = randomToken64();
    LoginToken lt = new LoginToken(token, user, Instant.now().plus(15, ChronoUnit.MINUTES));
    tokenRepo.save(lt);

    return baseUrl + "/auth/callback?token=" + token;
  }

    private String randomToken64() {
    byte[] bytes = new byte[32];
    random.nextBytes(bytes);
    return HexFormat.of().formatHex(bytes);
  }
}

