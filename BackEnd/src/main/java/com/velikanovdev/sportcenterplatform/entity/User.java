package com.velikanovdev.sportcenterplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public User(String name, String email, String passwordHash, String passwordSalt) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    public User(String name, String email, String passwordHash, String passwordSalt, SportClub sportClub) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.sportClub = sportClub;
    }

}
