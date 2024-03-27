package com.velikanovdev.sportcenterplatform.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SportsEventDTO(
        @NotBlank(message = "Description is required") String description,
        @NotNull(message = "Capacity is required") Integer capacity,
        @NotNull(message = "Availability is required") Boolean isAvailable,
        @NotNull(message = "Event type ID is required") Long eventTypeId,
        @NotNull(message = "Venue ID is required") Long venueId,
        @NotNull(message = "Trainer ID is required") Long trainerId
) {}
