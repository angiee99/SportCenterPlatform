package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<SportsEvent, Long> {
    Optional<SportsEvent> findById(Long id);
}
