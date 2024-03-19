package com.velikanovdev.sportcenterplatform.controller;

import com.velikanovdev.sportcenterplatform.dto.SportsEventDTO;
import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import com.velikanovdev.sportcenterplatform.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<?> createEvent(@RequestBody SportsEvent event) {
        if(event == null) {
            return ResponseEntity.badRequest().build();
        }
        SportsEvent createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok("Created event: " + createdEvent);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<SportsEvent> getEvent(@PathVariable Long id) {
        SportsEvent sportEvent = eventService.getEvent(id);
        if(sportEvent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sportEvent);
    }

    @GetMapping("/events")
    public ResponseEntity<List<SportsEventDTO>> getAllEvents() {
        List<SportsEventDTO> sportsEvents = eventService.getAllEvents();

        if(sportsEvents == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sportsEvents);
    }

    @GetMapping("/activeEvents")
    public ResponseEntity<List<SportsEventDTO>> getActiveEvents() {
        List<SportsEventDTO> activeEvents = eventService.getActiveEvents();

        if(activeEvents == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(activeEvents);
    }
}
