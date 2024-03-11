package com.velikanovdev.sportcenterplatform.entity;

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

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id", foreignKey = @ForeignKey(name = "FK_sports_events_event_types"))
    private EventType eventType;

    @OneToMany(mappedBy = "sportsEvent")
    private List<Schedule> schedules;

    // Many SportsEvents can be associated with one Venue
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", foreignKey = @ForeignKey(name = "FK_sports_events_venues"))
    private Venue venue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", foreignKey = @ForeignKey(name = "FK_sports_events_trainers"))
    private User trainer;
}
