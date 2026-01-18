package com.timeTracker.auth;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CurrentUser {
  public Optional<UUID> getUserId(HttpSession session) {
    Object v = session.getAttribute("userId");
    if (v == null) return Optional.empty();
    return Optional.of(UUID.fromString(v.toString()));
  }
}
