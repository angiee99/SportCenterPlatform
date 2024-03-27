package com.velikanovdev.sportcenterplatform.controller;

import com.velikanovdev.sportcenterplatform.dto.EventSignupDTO;
import com.velikanovdev.sportcenterplatform.service.EventSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventSignup")
public class EventSignupController {
    private final EventSignupService eventSignupService;

    @Autowired
    public EventSignupController(EventSignupService eventSignupService) {
        this.eventSignupService = eventSignupService;
    }

    @PostMapping("/register")
    public ResponseEntity<EventSignupDTO> register(@RequestParam Long userId, @RequestParam Long scheduleId) {
        EventSignupDTO eventSignup = eventSignupService.registerUserToSchedule(userId, scheduleId);

        if(eventSignup == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventSignup);
    }

    @PostMapping("/unregister")
    public ResponseEntity<?> unregister(@RequestParam Long userId, @RequestParam Long scheduleId) {
        boolean eventSignup = eventSignupService.unregisterUserFromSchedule(userId, scheduleId);

        if(eventSignup) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EventSignupDTO>> getUserEvents(@PathVariable Long id) {
        List<EventSignupDTO> eventSignupDTOS = eventSignupService.getUserEvents(id);

        if(eventSignupDTOS ==  null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventSignupDTOS);
    }
}
