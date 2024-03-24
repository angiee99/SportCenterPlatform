package com.velikanovdev.sportcenterplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<EventSignup> registrations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sports_event_id", foreignKey = @ForeignKey(name = "FK_schedules_sports_events"))
    private SportsEvent sportsEvent;

    public Schedule( LocalDateTime startTime, LocalDateTime endTime, SportsEvent sportsEvent) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.sportsEvent = sportsEvent;
    }

}
