package com.velikanovdev.sportcenterplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "schedule")
    private List<Registration> registrations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sports_event_id", foreignKey = @ForeignKey(name = "FK_schedules_sports_events"))
    private SportsEvent sportsEvent;

    public Schedule(Integer capacity, LocalDateTime startTime, LocalDateTime endTime, SportsEvent sportsEvent) {
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sportsEvent = sportsEvent;
    }

}
