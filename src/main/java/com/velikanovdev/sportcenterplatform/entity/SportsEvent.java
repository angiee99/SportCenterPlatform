package com.velikanovdev.sportcenterplatform.entity;

import com.velikanovdev.sportcenterplatform.entity.enums.SportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sports_events")
public class SportsEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacity;

    private String description;

    @OneToMany(mappedBy = "eventType")
    private List<EventType> types;

    @OneToMany(mappedBy = "sportsEvent")
    private List<Schedule> schedules;

    // Many SportsEvents can be associated with one Venue
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
}
