package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.EventSignup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventSignupRepository extends JpaRepository<EventSignup, Long> {
    Optional<EventSignup> findByUserIdAndAndScheduleId(Long userId, Long scheduleId);
}
