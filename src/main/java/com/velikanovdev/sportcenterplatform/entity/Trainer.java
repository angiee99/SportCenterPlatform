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
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_trainer_user_id"))
    private User user;

    @OneToMany(mappedBy = "personalTrainer")
    private List<User> trainees;

    @Enumerated(EnumType.STRING)
    private SportType specialization;

    @OneToMany(mappedBy = "trainer")
    private List<SportsEvent> sportsEvents;
}
