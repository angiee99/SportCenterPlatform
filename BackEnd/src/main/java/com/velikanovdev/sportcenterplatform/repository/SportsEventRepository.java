package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsEventRepository extends JpaRepository<SportsEvent, Long> {
}
