package com.velikanovdev.sportcenterplatform.service;

import com.velikanovdev.sportcenterplatform.dto.SportsEventDTO;
import com.velikanovdev.sportcenterplatform.dto.SportsEventInfoDTO;
import com.velikanovdev.sportcenterplatform.entity.SportsEvent;

import java.util.List;

public interface EventService {
    SportsEvent createEvent(SportsEventDTO eventDTO);
    SportsEventInfoDTO updateEvent(Long id, SportsEventDTO eventDTO);
    SportsEventInfoDTO getEvent(Long id);
    List<SportsEventInfoDTO> getAllEvents();
    List<SportsEventInfoDTO> getActiveEvents();
    void deleteEvent(Long id);
}
