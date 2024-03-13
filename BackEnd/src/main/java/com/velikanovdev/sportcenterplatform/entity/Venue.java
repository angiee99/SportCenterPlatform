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
@Table(name = "venues")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "floor")
    private Integer floor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_venues_address"))
    private Address address;

    // One Venue can be associated with many SportsEvents
    @OneToMany(mappedBy = "venue")
    private List<SportsEvent> sportsEvents;

    public Venue(Integer number, Integer floor, Address address) {
        this.number = number;
        this.floor = floor;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getFloor() {
        return floor;
    }

    public Address getAddress() {
        return address;
    }

    public List<SportsEvent> getSportsEvents() {
        return sportsEvents;
    }
}
