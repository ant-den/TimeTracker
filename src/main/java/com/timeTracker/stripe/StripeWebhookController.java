package com.timeTracker.stripe;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.timeTracker.user.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/billing")
public class StripeWebhookController {

  @Value("${stripe.webhook-secret}")
  private String webhookSecret;

  private final UserRepository userRepo;

  public StripeWebhookController(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @PostMapping("/webhook")
  public ResponseEntity<String> webhook(@RequestBody String payload,
                                       @RequestHeader("Stripe-Signature") String sigHeader) {
    try {
      Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);

      if ("checkout.session.completed".equals(event.getType())) {
        Session session = (Session) event.getDataObjectDeserializer()
            .getObject().orElse(null);

        if (session != null) {
          String userId = session.getMetadata().get("userId");
          if (userId != null) {
            var user = userRepo.findById(UUID.fromString(userId)).orElse(null);
            if (user != null) {
              user.setPaid(true);
              user.setPaidAt(Instant.now());
              userRepo.save(user);
            }
          }
        }
      }

      return ResponseEntity.ok("ok");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("bad");
    }
  }
}
