error id: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/work/WorkSession.java:_empty_/Column#nullable#
file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/work/WorkSession.java
empty definition using pc, found symbol in pc: _empty_/Column#nullable#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 442
uri: file:///C:/Users/user/Desktop/12x12/timeTracker/src/main/java/com/timeTracker/work/WorkSession.java
text:
```scala
package com.timeTracker.work;

import java.time.Instant;
import java.util.UUID;

import com.timeTracker.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "work_sessions")
@Getter
@Setter
public class WorkSession {
    
@Id
private UUID id;

@Column(nullab@@le = false)

private User user;

@Column(nullable = false)
private Instant startedAt;

private Instant endedAt;

@Column(columnDefinition = "text")
private String note;

protected WorkSession() {}

public WorkSession(UUID id, UUID userId, Instant startedAt) {
    this.id = id;
    this.userId = userId;
    this.startedAt = startedAt;
}
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Column#nullable#