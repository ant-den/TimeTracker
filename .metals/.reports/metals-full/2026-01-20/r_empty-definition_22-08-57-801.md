error id: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/session/SessionController.java:_empty_/SessionService#isRunning#
file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/session/SessionController.java
empty definition using pc, found symbol in pc: _empty_/SessionService#isRunning#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 973
uri: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/session/SessionController.java
text:
```scala
package com.timeTracker.session;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.timeTracker.auth.CurrentUser;
import com.timeTracker.work.WorkSessionRepository;

@Controller
public class SessionController {
  private final CurrentUser currentUser;
  private final SessionService service;
  private final WorkSessionRepository repo;

  public SessionController(CurrentUser currentUser, SessionService service, WorkSessionRepository repo) {
    this.currentUser = currentUser;
    this.service = service;
    this.repo = repo;
  }

  @GetMapping("/")
  public String index(HttpSession session, Model model) {
    var userId = currentUser.getUserId(session).orElse(null);
    if (userId == null){
      ystem.out.println("redirect rabotajet");
     return "redirect:/login";
    }
    model.addAttribute("running", service.isRunnin@@g(userId));
    model.addAttribute("sessions", repo.findTop50ByUserIdOrderByStartedAtDesc(userId));
    return "index";
  }

  @PostMapping("/session/start")
  public String start(HttpSession session) {
    var userId = currentUser.getUserId(session).orElseThrow();
    try {
      service.start(userId);
      return "redirect:/";
    } catch (PaywallException e) {
      return "redirect:/paywall";
    }
  }

  @PostMapping("/session/stop")
  public String stop(@RequestParam(required = false) String note, HttpSession session) {
    var userId = currentUser.getUserId(session).orElseThrow();
    service.stop(userId, note);
    return "redirect:/";
  }

  @GetMapping("/paywall")
  public String paywall() {
    return "paywall";
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/SessionService#isRunning#