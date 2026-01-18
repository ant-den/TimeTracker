error id: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/auth/MagicLinkService.java:_empty_/LoginToken#
file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/auth/MagicLinkService.java
empty definition using pc, found symbol in pc: _empty_/LoginToken#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1100
uri: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/auth/MagicLinkService.java
text:
```scala
package com.timeTracker.auth;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.timeTracker.login.LoginToken;
import com.timeTracker.login.LoginTokenRepo;
import com.timeTracker.user.User;
import com.timeTracker.user.UserRepo;


@Service
public class MagicLinkService {
    private final LoginTokenRepo tokenRepo;
    private final UserRepo userRepo;
    private final SecureRandom random = new SecureRandom();

    @Value("${app.base-url}")
    private String baseUrl;

    public MagicLinkService(UserRepo userRepo, LoginTokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }
    public String createMagicLink(String email) {
    User user = userRepo.findByEmail(email.toLowerCase().trim())
        .orElseGet(() -> userRepo.save(new User(UUID.randomUUID(), email)));

    String token = randomToken64();
    LoginToken lt = new Login@@Token(token, user.getId(), Instant.now().plus(15, ChronoUnit.MINUTES));
    tokenRepo.save(lt);

    return baseUrl + "/auth/callback?token=" + token;
  }

    private String randomToken64() {
    byte[] bytes = new byte[32];
    random.nextBytes(bytes);
    return HexFormat.of().formatHex(bytes);
  }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/LoginToken#