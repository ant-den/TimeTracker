error id: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/auth/AuthController.java:_empty_/MagicLinkService#createMagicLink#
file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/auth/AuthController.java
empty definition using pc, found symbol in pc: _empty_/MagicLinkService#createMagicLink#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 771
uri: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/auth/AuthController.java
text:
```scala
package com.timeTracker.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
        private final MagicLinkService magicLinkService;

    public AuthController(MagicLinkService magicLinkService) {
        this.magicLinkService = magicLinkService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String sendLink(@RequestParam String email, Model model) {
        String link = magicLinkService.createMa@@gicLink(email);
        // TODO: отправить email через Resend/SendGrid. Пока:
        System.out.println("MAGIC LINK: " + link);

        model.addAttribute("sent", true);
        return "login";
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/MagicLinkService#createMagicLink#