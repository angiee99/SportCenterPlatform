package com.velikanovdev.sportcenterplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSignupDTO {
    private Long id;
    private LocalDateTime registrationTime;
    private String username;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String eventDescription;
}
