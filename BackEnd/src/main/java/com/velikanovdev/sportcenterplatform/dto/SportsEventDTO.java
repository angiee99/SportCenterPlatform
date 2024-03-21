package com.velikanovdev.sportcenterplatform.dto;

import com.velikanovdev.sportcenterplatform.entity.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record SportsEventDTO(
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
