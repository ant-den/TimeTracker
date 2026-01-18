error id: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/stripe/StripeService.java:_empty_/Session#
file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/stripe/StripeService.java
empty definition using pc, found symbol in pc: _empty_/Session#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 960
uri: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/stripe/StripeService.java
text:
```scala
package com.timeTracker.stripe;



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

    Session session = @@Session.create(params);
    return session.getUrl();
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Session#