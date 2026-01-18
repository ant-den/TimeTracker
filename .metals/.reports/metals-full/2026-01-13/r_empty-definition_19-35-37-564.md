error id: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/session/SessionService.java:_empty_/WorkSessionRepository#findFirstByUserIdAndEndedAtIsNullOrderByStartTimeDesc#orElseThrow#setEndTime#
file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/session/SessionService.java
empty definition using pc, found symbol in pc: _empty_/WorkSessionRepository#findFirstByUserIdAndEndedAtIsNullOrderByStartTimeDesc#orElseThrow#setEndTime#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1393
uri: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/session/SessionService.java
text:
```scala
package com.timeTracker.session;


import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.timeTracker.user.UserRepository;
import com.timeTracker.work.WorkSession;
import com.timeTracker.work.WorkSessionRepository;


@Service
public class SessionService {
  private final WorkSessionRepository repo;
  private final UserRepository userRepo;

  @Value("${app.free-sessions-limit:3}")
  private int freeLimit;

  public SessionService(WorkSessionRepository repo, UserRepository userRepo) {
    this.repo = repo;
    this.userRepo = userRepo;
  }

  public void start(UUID userId) {
    var user = userRepo.findById(userId).orElseThrow();
    if (!user.isPaid()) {
      long count = repo.countByUserId(userId);
      if (count >= freeLimit) {
        throw new PaywallException();
      }
    }

    // не создаём вторую активную
    repo.findFirstByUserIdAndEndedAtIsNullOrderByStartTimeDesc(userId)
        .ifPresent(ws -> { throw new IllegalStateException("Already running"); });

    repo.save(new WorkSession(UUID.randomUUID(), userId, Instant.now()));
  }

  public void stop(UUID userId, String note) {
    var ws = repo.findFirstByUserIdAndEndedAtIsNullOrderByStartTimeDesc(userId)
        .orElseThrow(() -> new IllegalStateException("No active session"));

    ws.setEndT@@ime(Instant.now());
    if (note != null && !note.isBlank()) ws.setNote(note.trim());
    repo.save(ws);
  }

  public boolean isRunning(UUID userId) {
    return repo.findFirstByUserIdAndEndedAtIsNullOrderByStartTimeDesc(userId).isPresent();
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/WorkSessionRepository#findFirstByUserIdAndEndedAtIsNullOrderByStartTimeDesc#orElseThrow#setEndTime#