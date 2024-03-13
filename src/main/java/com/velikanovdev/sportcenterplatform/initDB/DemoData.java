package com.velikanovdev.sportcenterplatform.initDB;

import com.velikanovdev.sportcenterplatform.entity.Address;
import com.velikanovdev.sportcenterplatform.entity.EventType;
import com.velikanovdev.sportcenterplatform.entity.Venue;
import com.velikanovdev.sportcenterplatform.entity.enums.SportType;
import com.velikanovdev.sportcenterplatform.repository.AddressesRepository;
import com.velikanovdev.sportcenterplatform.repository.EventTypeRepository;
import com.velikanovdev.sportcenterplatform.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoData implements ApplicationRunner {
    private final AddressesRepository addressesRepository;
    private final VenueRepository venueRepository;
    private final EventTypeRepository eventTypeRepository;
    @Autowired
    public DemoData(AddressesRepository addressesRepository, VenueRepository venueRepository, EventTypeRepository eventTypeRepository) {
        this.addressesRepository = addressesRepository;
        this.venueRepository = venueRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Address address1 = new Address( "23456", "Palachova", "1", "Hradec Kralove");
        Address address2 = new Address( "23400", "Benesova", "306", "Hradec Kralove");
        addressesRepository.saveAll(List.of(address1, address2));

        Venue venue1 = new Venue(12, 0, address1);
        Venue venue2 = new Venue(905, 0, address2);
        venueRepository.saveAll(List.of(venue1, venue2));

        EventType eventType1 = new EventType(SportType.TENNIS, "18+", "Tennis play");
        EventType eventType2 = new EventType(SportType.TENNIS, "9 - 15", "Tennis play for teenagers");
        EventType eventType3 = new EventType(SportType.VOLLEYBALL, "25+", "Volleyball play for adults");
        eventTypeRepository.saveAll(List.of(eventType1, eventType2, eventType3));



    }
}