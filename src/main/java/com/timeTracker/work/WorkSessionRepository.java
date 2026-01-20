package com.timeTracker.work;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkSessionRepository extends JpaRepository<WorkSession, UUID> {
    List<WorkSession> findTop50ByUserIdOrderByStartedAtDesc(UUID userId);

    @Query("select count(ws) from WorkSession ws where ws.user.id = :userId")
    long countByUserId(@Param("userId")UUID userId);

    Optional<WorkSession> findFirstByUserIdAndEndedAtIsNullOrderByStartedAtDesc(UUID userId);
}