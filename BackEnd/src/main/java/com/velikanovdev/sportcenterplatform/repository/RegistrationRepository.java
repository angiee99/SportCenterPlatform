package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.Registration;
import com.velikanovdev.sportcenterplatform.entity.Schedule;
import com.velikanovdev.sportcenterplatform.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Modifying
    @Transactional
    @Query(
            value = " DELETE FROM registrations " +
                    " WHERE registrations.user_id = ?1 AND registrations.schedule_id = ?2",
            nativeQuery = true
    )
    void deleteByUserAndSchedule(Long userId, Long scheduleId);
}
