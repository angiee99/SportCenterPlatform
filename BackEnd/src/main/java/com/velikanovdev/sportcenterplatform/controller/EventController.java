package com.velikanovdev.sportcenterplatform.controller;

import com.velikanovdev.sportcenterplatform.entity.SportsEvent;
import com.velikanovdev.sportcenterplatform.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SportsEvent> getEventDetails(@PathVariable Long id) {
        SportsEvent sportEvent = eventService.showEventDetails(id);
        if(sportEvent == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sportEvent);
    }
}
