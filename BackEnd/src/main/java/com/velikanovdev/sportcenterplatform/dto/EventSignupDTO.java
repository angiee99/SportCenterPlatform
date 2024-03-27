package com.velikanovdev.sportcenterplatform.dto;

import java.time.LocalDateTime;

public record EventSignupDTO (
        Long id,
        LocalDateTime registrationTime,
        String username,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String eventDescription
) {}
