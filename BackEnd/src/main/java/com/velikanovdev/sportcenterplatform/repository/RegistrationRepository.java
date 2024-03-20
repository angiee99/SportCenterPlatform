package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.Registration;
import com.velikanovdev.sportcenterplatform.entity.Schedule;
import com.velikanovdev.sportcenterplatform.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Modifying
    @Transactional
    @Query(
            value = " DELETE FROM registrations " +
                    " WHERE registrations.user_id = ?1 AND registrations.schedule_id = ?2",
            nativeQuery = true
    )
    void deleteByUserAndSchedule(Long userId, Long scheduleId);
    @Query(
            value = " SELECT r FROM registrations r" +
                    " WHERE registrations.user_id = ?1",
            nativeQuery = true
    )
    List<Registration> getRegistrationsByUserId(Long userId);
}
