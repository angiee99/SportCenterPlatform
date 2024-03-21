package com.velikanovdev.sportcenterplatform.service;

import com.velikanovdev.sportcenterplatform.dto.SportsEventDTO;
import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import com.velikanovdev.sportcenterplatform.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

//    @Transactional
    public SportsEventDTO updateEvent(Long id, SportsEvent sportsEvent) {
        SportsEvent sportsEventToUpdate = eventRepository.findById(id).orElse(null);

        if(sportsEventToUpdate == null) {
            return null;
        }

        sportsEventToUpdate.setDescription(sportsEvent.getDescription());
        sportsEventToUpdate.setCapacity(sportsEvent.getCapacity());
        sportsEventToUpdate.setIsAvailable(sportsEvent.getIsAvailable());
        sportsEventToUpdate.setEventType(sportsEvent.getEventType());
        sportsEventToUpdate.setSchedules(sportsEvent.getSchedules());
        sportsEventToUpdate.setVenue(sportsEvent.getVenue());
        sportsEventToUpdate.setTrainer(sportsEvent.getTrainer());

        SportsEvent updatedEvent = eventRepository.save(sportsEventToUpdate);
        SportsEventDTO sportsEventDTO = convertSportsEventsToDTO(updatedEvent);
        System.out.println(sportsEventDTO);
        return sportsEventDTO;
    }

    public SportsEventDTO getEvent(Long id) {
        SportsEvent sportsEvent = eventRepository.findById(id).orElse(null);

        if(sportsEvent == null) {
            return null;
        }

        return convertSportsEventsToDTO(sportsEvent);

    }

    public List<SportsEventDTO> getAllEvents() {
        List<SportsEvent> sportsEvents = eventRepository.findAll();
        List<SportsEventDTO> eventDTOS = sportsEvents
                .stream()
                .map(this::convertSportsEventsToDTO)
                .collect(Collectors.toList());

        if(eventDTOS.isEmpty()) {
            return null;
        }

        return eventDTOS;
    }

    public List<SportsEventDTO> getActiveEvents() {
        List<SportsEvent> sportsEvents = eventRepository.findByIsAvailableTrue();

        List<SportsEventDTO> eventDTOS = sportsEvents
                .stream()
                .map(this::convertSportsEventsToDTO)
                .collect(Collectors.toList());

        if (eventDTOS.isEmpty()) {
            return null;
        }

        return eventDTOS;
    }

    private SportsEventDTO convertSportsEventsToDTO(SportsEvent event) {

        return new SportsEventDTO(
                event.getId(),
                event.getDescription(),
                event.getCapacity(),
                event.getIsAvailable(),
                event.getEventType().getSport(),
                event.getEventType().getAgeRestriction(),
                event.getEventType().getDescription(),
                event.getVenue().toString(),
                event.getTrainer().getName()
        );
    }
}
