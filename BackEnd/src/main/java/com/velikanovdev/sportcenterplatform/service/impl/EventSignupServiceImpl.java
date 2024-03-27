package com.velikanovdev.sportcenterplatform.service.impl;

import com.velikanovdev.sportcenterplatform.dto.EventSignupDTO;
import com.velikanovdev.sportcenterplatform.entity.EventSignup;
import com.velikanovdev.sportcenterplatform.entity.Schedule;
import com.velikanovdev.sportcenterplatform.entity.User;
import com.velikanovdev.sportcenterplatform.repository.EventSignupRepository;
import com.velikanovdev.sportcenterplatform.repository.ScheduleRepository;
import com.velikanovdev.sportcenterplatform.repository.UserRepository;
import com.velikanovdev.sportcenterplatform.service.EventSignupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventSignupServiceImpl implements EventSignupService {
    private final EventSignupRepository eventSignupRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public EventSignupServiceImpl(EventSignupRepository eventSignupRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.eventSignupRepository = eventSignupRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public EventSignupDTO registerUserToSchedule(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with ID: " + scheduleId));

        if(eventSignupRepository.findByUserIdAndAndScheduleId(userId, scheduleId).isPresent()) {
            throw new IllegalStateException("User is already signed up for this schedule");
        }

        EventSignup eventSignup = new EventSignup(LocalDateTime.now(), user, schedule);
        eventSignupRepository.save(eventSignup);
        return convertEventSignupsToDTOs(eventSignup);
    }

    @Override
    public boolean unregisterUserFromSchedule(Long userId, Long scheduleId) {
        EventSignup eventSignup = eventSignupRepository.findByUserIdAndAndScheduleId(userId, scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Event Signup for user with ID: " + userId +
                                " and Schedule with ID: " + scheduleId + " was not found"));

        eventSignupRepository.delete(eventSignup);
        return true;
    }

    @Override
    public List<EventSignupDTO> getUserEvents(Long userId) {
        List<EventSignup> eventSignups = eventSignupRepository.getEventSignupByUserId(userId);
        return eventSignups
                .stream()
                .map(this::convertEventSignupsToDTOs)
                .collect(Collectors.toList());
    }

    private EventSignupDTO convertEventSignupsToDTOs(EventSignup eventSignup) {
        return new EventSignupDTO(
                eventSignup.getId(),
                eventSignup.getRegistrationTime(),
                eventSignup.getUser().getUsername(),
                eventSignup.getSchedule().getStartTime(),
                eventSignup.getSchedule().getEndTime(),
                eventSignup.getSchedule().getSportsEvent().getDescription()
        );
    }
}
