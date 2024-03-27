package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.EventSignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventSignupRepository extends JpaRepository<EventSignup, Long> {
    Optional<EventSignup> findByUserIdAndAndScheduleId(Long userId, Long scheduleId);
    @Query(
            value = "SELECT * FROM event_signup WHERE user_id = ?1",
            nativeQuery = true
    )
    List<EventSignup> getEventSignupByUserId(Long userId);
}
