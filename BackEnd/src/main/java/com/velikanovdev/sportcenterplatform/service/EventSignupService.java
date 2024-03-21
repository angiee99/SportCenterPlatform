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
import java.util.List;
import java.util.stream.Collectors;

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
        return convertEventSignupsToDTOs(eventSignup);
    }

    public boolean unregisterUserFromSchedule(Long userId, Long scheduleId) {
        EventSignup eventSignup = eventSignupRepository.findByUserIdAndAndScheduleId(userId, scheduleId).orElse(null);

        if(eventSignup == null) {
            return false;
        }

        eventSignupRepository.delete(eventSignup);
        return true;
    }

    public List<EventSignupDTO> getUserEvents(Long userId) {
        List<EventSignup> eventSignups = eventSignupRepository.getEventSignupByUserId(userId);
        List<EventSignupDTO> eventSignupDTOS = eventSignups
                .stream()
                .map(this::convertEventSignupsToDTOs)
                .collect(Collectors.toList());

        if(eventSignupDTOS.isEmpty()) {
            return null;
        }

        return eventSignupDTOS;
    }

    private EventSignupDTO convertEventSignupsToDTOs(EventSignup eventSignup) {
        return new EventSignupDTO(
                eventSignup.getId(),
                eventSignup.getRegistrationTime(),
                eventSignup.getUser().getName(),
                eventSignup.getSchedule().getStartTime(),
                eventSignup.getSchedule().getEndTime(),
                eventSignup.getSchedule().getSportsEvent().getDescription()
        );
    }
}
