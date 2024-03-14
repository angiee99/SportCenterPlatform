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
@Table(name = "sport_clubs")
public class SportClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo")
    private byte[] logo;  // Assuming the logo is stored as a binary data

    // Assuming a sports club can have multiple users
    @OneToMany(mappedBy = "sportClub")
    private List<User> users;

    public SportClub(String name) {
        this.name = name;
    }

    public SportClub(String name, byte[] logo) {
        this.name = name;
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public List<User> getUsers() {
        return users;
    }
}
