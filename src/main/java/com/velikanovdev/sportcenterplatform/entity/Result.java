package com.velikanovdev.sportcenterplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Assuming rating is stored as JSON String
    @Column(name = "rating")
    private String rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sports_event_id", foreignKey = @ForeignKey(name = "FK_results_sports_events"))
    private SportsEvent sportsEvent;
}
