package com.velikanovdev.sportcenterplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sports_events")
public class SportsEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "isAvailable")
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id", foreignKey = @ForeignKey(name = "FK_sports_events_event_types"))
    private EventType eventType;

    @OneToMany(mappedBy = "sportsEvent", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    // Many SportsEvents can be associated with one Venue
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", foreignKey = @ForeignKey(name = "FK_sports_events_venues"))
    private Venue venue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", foreignKey = @ForeignKey(name = "FK_sports_events_trainers"))
    private User trainer;

    public SportsEvent(String description, Boolean isAvailable, Integer capacity, EventType eventType, Venue venue, User trainer) {
        this.description = description;
        this.isAvailable = isAvailable;
        this.capacity = capacity;
        this.eventType = eventType;
        this.venue = venue;
        this.trainer = trainer;
    }

}
