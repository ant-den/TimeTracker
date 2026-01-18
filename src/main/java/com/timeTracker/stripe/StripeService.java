package com.timeTracker.stripe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class StripeService {

  @Value("${stripe.secret-key}")
  private String secretKey;

  @Value("${stripe.price-id}")
  private String priceId;

  @Value("${app.base-url}")
  private String baseUrl;

  public String createCheckoutUrl(String userId) throws Exception {
    Stripe.apiKey = secretKey;

    SessionCreateParams params =
        SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl(baseUrl + "/?paid=1")
            .setCancelUrl(baseUrl + "/paywall?canceled=1")
            .addLineItem(
                SessionCreateParams.LineItem.builder()
                    .setPrice(priceId)
                    .setQuantity(1L)
                    .build())
            // metadata — чтобы в webhook понять, кому выдать доступ
            .putMetadata("userId", userId)
            .build();

    Session session = Session.create(params);
    return session.getUrl();
  }
}
