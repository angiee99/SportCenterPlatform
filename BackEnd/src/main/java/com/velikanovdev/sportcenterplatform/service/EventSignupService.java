package com.velikanovdev.sportcenterplatform.service;

import com.velikanovdev.sportcenterplatform.dto.EventSignupDTO;

import java.util.List;

public interface EventSignupService {
    EventSignupDTO registerUserToSchedule(Long userId, Long scheduleId);
    boolean unregisterUserFromSchedule(Long userId, Long scheduleId);
    List<EventSignupDTO> getUserEvents(Long userId);
}
