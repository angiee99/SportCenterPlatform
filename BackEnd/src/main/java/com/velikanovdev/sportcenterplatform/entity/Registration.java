package com.velikanovdev.sportcenterplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_time")
    private LocalDateTime registrationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_registrations_users"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", foreignKey = @ForeignKey(name = "FK_registrations_schedules"))
    private Schedule schedule;

    public Registration(LocalDateTime registrationTime, User user, Schedule schedule) {
        this.registrationTime = registrationTime;
        this.user = user;
        this.schedule = schedule;
    }

}
