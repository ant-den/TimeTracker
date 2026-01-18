package com.timeTracker.stripe;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.timeTracker.auth.CurrentUser;

@Controller
public class BillingController {
  private final StripeService stripeService;
  private final CurrentUser currentUser;

  public BillingController(StripeService stripeService, CurrentUser currentUser) {
    this.stripeService = stripeService;
    this.currentUser = currentUser;
  }

  @PostMapping("/billing/checkout")
  public String checkout(HttpSession session) throws Exception {
    var userId = currentUser.getUserId(session).orElseThrow();
    String url = stripeService.createCheckoutUrl(userId.toString());
    return "redirect:" + url;
  }
}
