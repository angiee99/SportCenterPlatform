package com.velikanovdev.sportcenterplatform.service;

import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import com.velikanovdev.sportcenterplatform.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public SportsEvent createEvent(SportsEvent event) {
        return eventRepository.save(event);
    }

    public SportsEvent showEventDetails(Long id) {
        Optional<SportsEvent> sportsEvent = eventRepository.findById(id);
        return sportsEvent.orElse(null);
    }
}
