package com.velikanovdev.sportcenterplatform.dto;

import com.velikanovdev.sportcenterplatform.entity.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportsEventDTO {
    private Long id;
    private String description;
    private Integer capacity;
    private Boolean isAvailable;
    private SportType sportType;
    private String ageRestriction;
    private String eventTypeDescription;
    private String venueInfo;
    private String trainerName;
}
