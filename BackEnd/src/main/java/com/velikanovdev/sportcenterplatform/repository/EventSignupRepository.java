package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.EventSignup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSignupRepository extends JpaRepository<EventSignup, Long> {
}
