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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_name", nullable = false, unique = true)
    private String loginName;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "password_salt", nullable = false)
    private String passwordSalt;

    @OneToMany(mappedBy = "trainer")
    private List<SportsEvent> sportsEvents;

    // Assuming a user can have multiple registrations
    @OneToMany(mappedBy = "user")
    private List<Registration> registrations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_club_id", foreignKey = @ForeignKey(name = "FK_users_sport_clubs"))
    private SportClub sportClub;
}
