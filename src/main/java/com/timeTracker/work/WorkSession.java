package com.timeTracker.work;

import java.time.Instant;
import java.util.UUID;

import com.timeTracker.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;

@Column(nullable = false)
private Instant startedAt;

private Instant endedAt;

@Column(columnDefinition = "text")
private String note;

@Column(nullable = false)
private Instant createdAt = Instant.now();

protected WorkSession() {}

public WorkSession(UUID id, User user, Instant startedAt) {
    this.id = id;
    this.user = user;
    this.startedAt = startedAt;
    this.createdAt = Instant.now();
}
}
