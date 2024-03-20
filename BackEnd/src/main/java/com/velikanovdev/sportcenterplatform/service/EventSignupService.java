package com.velikanovdev.sportcenterplatform.service;

import com.velikanovdev.sportcenterplatform.dto.EventSignupDTO;
import com.velikanovdev.sportcenterplatform.entity.EventSignup;
import com.velikanovdev.sportcenterplatform.entity.Schedule;
import com.velikanovdev.sportcenterplatform.entity.User;
import com.velikanovdev.sportcenterplatform.repository.EventSignupRepository;
import com.velikanovdev.sportcenterplatform.repository.ScheduleRepository;
import com.velikanovdev.sportcenterplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventSignupService {
    private final EventSignupRepository eventSignupRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public EventSignupService(EventSignupRepository eventSignupRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.eventSignupRepository = eventSignupRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public EventSignupDTO registerUserToSchedule(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId).orElse(null);
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);

        if(user == null || schedule == null) {
            return null;
        }

        EventSignup eventSignup = new EventSignup(LocalDateTime.now(), user, schedule);
        eventSignupRepository.save(eventSignup);
        return convertEventSignupToDTO(eventSignup);
    }

    public boolean unregisterUserFromSchedule(Long userId, Long scheduleId) {
        EventSignup eventSignup = eventSignupRepository.findByUserIdAndAndScheduleId(userId, scheduleId).orElse(null);

        if(eventSignup == null) {
            return false;
        }

        eventSignupRepository.delete(eventSignup);
        return true;
    }

    private EventSignupDTO convertEventSignupToDTO(EventSignup eventSignup) {
        EventSignupDTO dto = new EventSignupDTO();
        dto.setId(eventSignup.getId());
        dto.setRegistrationTime(eventSignup.getRegistrationTime());
        dto.setUsername(eventSignup.getUser().getName());
        dto.setStartTime(eventSignup.getSchedule().getStartTime());
        dto.setEndTime(eventSignup.getSchedule().getEndTime());
        dto.setEventDescription(eventSignup.getSchedule().getSportsEvent().getDescription());

        return dto;
    }
}
