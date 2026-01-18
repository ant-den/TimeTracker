package com.timeTracker.work;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkSessionRepository extends JpaRepository<WorkSession, UUID> {
    List<WorkSession> findTop50ByUserIdOrderByStartedAtDesc(UUID userId);

    @Query("select count(ws) from WorkSession ws where ws.userId = :userId")
    long countByUserId(UUID userId);

    Optional<WorkSession> findFirstByUserIdAndEndedAtIsNullOrderByStartTimeDesc(UUID userId);
}