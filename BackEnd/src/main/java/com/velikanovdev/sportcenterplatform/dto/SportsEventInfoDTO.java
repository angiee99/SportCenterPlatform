package com.velikanovdev.sportcenterplatform.dto;

import com.velikanovdev.sportcenterplatform.entity.enums.SportType;

public record SportsEventInfoDTO(
        Long id,
        String description,
        Integer capacity,
        Boolean isAvailable,
        SportType sportType,
        String ageRestriction,
        String eventTypeDescription,
        String venueInfo,
        String trainerName
) {}
