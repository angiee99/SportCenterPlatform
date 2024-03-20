package com.velikanovdev.sportcenterplatform.service;

import com.velikanovdev.sportcenterplatform.dto.SportsEventDTO;
import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import com.velikanovdev.sportcenterplatform.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public SportsEvent getEvent(Long id) {
        Optional<SportsEvent> sportsEvent = eventRepository.findById(id);
        return sportsEvent.orElse(null);
    }

    public List<SportsEventDTO> getAllEvents() {
        List<SportsEvent> sportsEvents = eventRepository.findAll();
        List<SportsEventDTO> eventDTOS = convertSportsEventToDTO(sportsEvents);

        if(eventDTOS.isEmpty()) {
            return null;
        }

        return eventDTOS;
    }

    public List<SportsEventDTO> getActiveEvents() {
        List<SportsEvent> sportsEvents = eventRepository.findByIsAvailableTrue();
        List<SportsEventDTO> eventDTOS = convertSportsEventToDTO(sportsEvents);


        if (eventDTOS.isEmpty()) {
            return null;
        }

        return eventDTOS;
    }

    private List<SportsEventDTO> convertSportsEventToDTO(List<SportsEvent> sportsEvents) {
        List<SportsEventDTO> eventDTOS = new ArrayList<>();

        for(SportsEvent event: sportsEvents) {
            SportsEventDTO eventDTO = new SportsEventDTO();
            eventDTO.setId(event.getId());
            eventDTO.setDescription(event.getDescription());
            eventDTO.setCapacity(event.getCapacity());
            eventDTO.setIsAvailable(event.isAvailable());
            eventDTO.setSportType(event.getEventType().getSport());
            eventDTO.setAgeRestriction(event.getEventType().getAgeRestriction());
            eventDTO.setEventTypeDescription(event.getEventType().getDescription());
            eventDTO.setVenueInfo(event.getVenue().toString());
            eventDTO.setTrainerName(event.getTrainer().getName());
            eventDTOS.add(eventDTO);
        }

        return eventDTOS;
    }
}
