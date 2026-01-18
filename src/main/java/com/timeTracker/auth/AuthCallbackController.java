package com.timeTracker.auth;

import java.time.Instant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.timeTracker.login.LoginTokenRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthCallbackController {
    private final LoginTokenRepository tokenRepo;

  public AuthCallbackController(LoginTokenRepository tokenRepo) {
    this.tokenRepo = tokenRepo;
  }

  @GetMapping("/callback")
  public String callback(@RequestParam String token, HttpSession session) {
    var lt = tokenRepo.findById(token).orElse(null);
    if (lt == null) return "redirect:/login?e=invalid";

    if (lt.getUsedAt() != null) return "redirect:/login?e=used";
    if (lt.getExpiredAt().isBefore(Instant.now())) return "redirect:/login?e=expired";

    lt.setUsedAt(Instant.now());
    tokenRepo.save(lt);

    session.setAttribute("userId", lt.getUserId().toString());
    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/login";
  }
}
