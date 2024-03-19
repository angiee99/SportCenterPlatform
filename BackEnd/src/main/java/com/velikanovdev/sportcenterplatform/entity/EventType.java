package com.velikanovdev.sportcenterplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.velikanovdev.sportcenterplatform.entity.enums.SportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "event_types")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SportType sport;

    @Column(name = "age_restriction")
    private String ageRestriction;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "eventType")
    private List<SportsEvent> sportsEvents;

    public EventType(SportType sport, String ageRestriction, String description) {
        this.sport = sport;
        this.ageRestriction = ageRestriction;
        this.description = description;
    }
}
