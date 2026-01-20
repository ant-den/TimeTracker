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
    private final LoginTokenRepository loginToken;

  public AuthCallbackController(LoginTokenRepository loginTokenRepo) {
    this.loginToken = loginTokenRepo;
  }

  @GetMapping("/callback")
  public String callback(@RequestParam String token, HttpSession session) {
    var lt = loginToken.findById(token).orElse(null);
    if (lt == null) return "redirect:/login?e=invalid";

    if (lt.getUsedAt() != null) return "redirect:/login?e=used";
    if (lt.getExpiredAt().isBefore(Instant.now())) return "redirect:/login?e=expired";

    lt.setUsedAt(Instant.now());
    loginToken.save(lt);

    session.setAttribute("userId", lt.getUser().getId().toString());
    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/login";
  }
}
