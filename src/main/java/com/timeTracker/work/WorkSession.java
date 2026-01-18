package com.timeTracker.work;

import java.time.Instant;
import java.util.UUID;

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

@Column(nullable = false)
private UUID userId;

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
