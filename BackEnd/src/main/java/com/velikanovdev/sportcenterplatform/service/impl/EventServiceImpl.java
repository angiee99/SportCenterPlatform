package com.velikanovdev.sportcenterplatform.service.impl;

import com.velikanovdev.sportcenterplatform.dto.SportsEventDTO;
import com.velikanovdev.sportcenterplatform.dto.SportsEventInfoDTO;
import com.velikanovdev.sportcenterplatform.entity.EventType;
import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import com.velikanovdev.sportcenterplatform.entity.User;
import com.velikanovdev.sportcenterplatform.entity.Venue;
import com.velikanovdev.sportcenterplatform.repository.EventRepository;
import com.velikanovdev.sportcenterplatform.repository.EventTypeRepository;
import com.velikanovdev.sportcenterplatform.repository.UserRepository;
import com.velikanovdev.sportcenterplatform.repository.VenueRepository;
import com.velikanovdev.sportcenterplatform.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;
    private final UserRepository userRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventTypeRepository eventTypeRepository, UserRepository userRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.userRepository = userRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    @Transactional
    public SportsEvent createEvent(SportsEventDTO eventDTO) {
        SportsEvent sportsEvent = convertToEntity(eventDTO);
        return eventRepository.save(sportsEvent);
    }

    @Override
    @Transactional
    public SportsEventInfoDTO updateEvent(Long id, SportsEventDTO eventDTO) {
        SportsEvent sportsEventToUpdate = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SportsEvent not found with ID: " + id));
        convertToEntityForUpdate(sportsEventToUpdate, eventDTO);

        SportsEvent updatedEvent = eventRepository.save(sportsEventToUpdate);
        return convertToDTO(updatedEvent);
    }


    @Override
    public SportsEventInfoDTO getEvent(Long id) {
        SportsEvent sportsEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SportsEvent not found with ID: " + id));
        return convertToDTO(sportsEvent);
    }

    @Override
    public List<SportsEventInfoDTO> getAllEvents() {
        List<SportsEvent> sportsEvents = eventRepository.findAll();
        return sportsEvents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SportsEventInfoDTO> getActiveEvents() {
        List<SportsEvent> sportsEvents = eventRepository.findByIsAvailableTrue();
        return sportsEvents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        SportsEvent eventToDelete = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SportsEvent not found with ID: " + id));
        eventRepository.deleteById(id);
    }

    private SportsEventInfoDTO convertToDTO(SportsEvent event) {

        return new SportsEventInfoDTO(
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

    private SportsEvent convertToEntity(SportsEventDTO eventDTO) {
        EventType eventType = eventTypeRepository.findById(eventDTO.eventTypeId())
                .orElseThrow(() -> new EntityNotFoundException("EventType not found with ID: " + eventDTO.eventTypeId()));
        Venue venue = venueRepository.findById(eventDTO.venueId())
                .orElseThrow(() -> new EntityNotFoundException("Venue not found with ID: " + eventDTO.venueId()));
        User trainer = userRepository.findById(eventDTO.trainerId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found with ID: " + eventDTO.trainerId()));

        return new SportsEvent(eventDTO.description(), eventDTO.isAvailable(), eventDTO.capacity(), eventType, venue, trainer);
    }

    private void convertToEntityForUpdate(SportsEvent sportsEvent, SportsEventDTO eventDTO) {
        sportsEvent.setDescription(eventDTO.description());
        sportsEvent.setCapacity(eventDTO.capacity());
        sportsEvent.setIsAvailable(eventDTO.isAvailable());

        EventType eventType = eventTypeRepository.findById(eventDTO.eventTypeId())
                .orElseThrow(() -> new EntityNotFoundException("EventType not found with ID: " + eventDTO.eventTypeId()));
        sportsEvent.setEventType(eventType);

        Venue venue = venueRepository.findById(eventDTO.venueId())
                .orElseThrow(() -> new EntityNotFoundException("Venue not found with ID: " + eventDTO.venueId()));
        sportsEvent.setVenue(venue);

        User trainer = userRepository.findById(eventDTO.trainerId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found with ID: " + eventDTO.trainerId()));
        sportsEvent.setTrainer(trainer);

    }
}
