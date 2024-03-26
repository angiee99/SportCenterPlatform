package com.velikanovdev.sportcenterplatform.controller;

import com.velikanovdev.sportcenterplatform.dto.SportsEventDTO;
import com.velikanovdev.sportcenterplatform.dto.SportsEventInfoDTO;
import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import com.velikanovdev.sportcenterplatform.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@Valid @RequestBody SportsEventDTO eventDTO) {
        if(eventDTO == null) {
            return ResponseEntity.badRequest().body("Request body is empty");
        }

        SportsEvent createdEvent = eventService.createEvent(eventDTO);
        return ResponseEntity.ok("Created event with id " + createdEvent.getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SportsEventInfoDTO> updateEvent(@PathVariable Long id, @RequestBody SportsEventDTO eventDTO) {
        SportsEventInfoDTO editedSportsEvent = eventService.updateEvent(id, eventDTO);

        if(editedSportsEvent == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(editedSportsEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportsEventInfoDTO> getEvent(@PathVariable Long id) {
        SportsEventInfoDTO sportEvent = eventService.getEvent(id);
        if(sportEvent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sportEvent);
    }

    @GetMapping("/allEvents")
    public ResponseEntity<List<SportsEventInfoDTO>> getAllEvents() {
        List<SportsEventInfoDTO> sportsEvents = eventService.getAllEvents();

        if(sportsEvents == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sportsEvents);
    }

    @GetMapping("/activeEvents")
    public ResponseEntity<List<SportsEventInfoDTO>> getActiveEvents() {
        List<SportsEventInfoDTO> activeEvents = eventService.getActiveEvents();

        if(activeEvents == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(activeEvents);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);

        return ResponseEntity.ok("Sports Event with id " + id + " was successfully deleted");
    }
}
