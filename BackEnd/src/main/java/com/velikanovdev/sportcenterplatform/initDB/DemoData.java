package com.velikanovdev.sportcenterplatform.initDB;

import com.velikanovdev.sportcenterplatform.entity.*;
import com.velikanovdev.sportcenterplatform.entity.enums.SportType;
import com.velikanovdev.sportcenterplatform.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DemoData implements ApplicationRunner {
    private final AddressesRepository addressesRepository;
    private final VenueRepository venueRepository;
    private final EventTypeRepository eventTypeRepository;
    private final SportClubRepository sportClubRepository;
    private final UserRepository userRepository;
    private final SportsEventRepository sportsEventRepository;
    private final ScheduleRepository scheduleRepository;
    private final EventSignupRepository eventSignupRepository;
    private final ResultRepository resultRepository;

    private boolean enabled;
    @Autowired
    public DemoData(AddressesRepository addressesRepository, VenueRepository venueRepository, EventTypeRepository eventTypeRepository, SportClubRepository sportClubRepository, UserRepository userRepository, SportsEventRepository sportsEventRepository,
                    ScheduleRepository scheduleRepository, EventSignupRepository eventSignupRepository, ResultRepository resultRepository) {
        this.addressesRepository = addressesRepository;
        this.venueRepository = venueRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.sportClubRepository = sportClubRepository;
        this.userRepository = userRepository;
        this.sportsEventRepository = sportsEventRepository;
        this.scheduleRepository = scheduleRepository;
        this.eventSignupRepository = eventSignupRepository;
        this.resultRepository = resultRepository;
        this.enabled = true;
    }

    @Override
    public void run(ApplicationArguments args) {
        if(!enabled) return;
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

        File sokolLogo = new File("src/main/resources/data/sokol.jpeg");
        byte[] byteArray = new byte[(int) sokolLogo.length()];

        SportClub club1 = new SportClub("Tigers");
        SportClub club2 = new SportClub("SOKOL",byteArray);
        sportClubRepository.saveAll(List.of(club1, club2));

        User user1 = new User("Jake", "jakethedog@gmail.com",
                "iloveladyrainicorn", "difoG89kfdgs'$h", club2);
        User user2 = new User("Finn", "finnthehuman@gmail.com",
                "enchiridion78", "pdisJoewlNKl090_ldf");
        User user3 = new User("Bubblegum", "bonnibelle@gmail.com",
                "timitimi1", "kfdlIhpo6^K;lsdkf");
        User user4 = new User("Marceline", "marcythequeen@gmail.com",
                "dontTakeMyFries", "78kdoAAwiefl8-dk");
        userRepository.saveAll(List.of(user1, user2 ,user3, user4));

        SportsEvent event1 = new SportsEvent("Game kick-off!", false, 20, eventType1, venue1, user1);
        SportsEvent event2 = new SportsEvent("Get some rest from school – come&play", true, 36, eventType2, venue2, user2);
        sportsEventRepository.saveAll(List.of(event1, event2));

        Schedule schedule1_1 = new Schedule( LocalDateTime.of(2024, 4, 12, 13, 0),
                LocalDateTime.of(2024, 4, 12, 15, 0),
                event1);
        Schedule schedule1_2 = new Schedule( LocalDateTime.of(2024, 4, 12, 16, 0),
                LocalDateTime.of(2024, 4, 15, 18, 0),
                event1);
        Schedule schedule2 = new Schedule( LocalDateTime.of(2024, 4, 5, 16, 30),
                LocalDateTime.of(2024, 4, 5, 18, 0),
                event2);
        Schedule schedule3 = new Schedule( LocalDateTime.of(2024, 3, 14, 16, 30),
                LocalDateTime.of(2024, 3, 14, 18, 0),
                event2);
        scheduleRepository.saveAll(List.of(schedule1_1, schedule1_2, schedule2, schedule3));

        EventSignup registration1 =  new EventSignup(LocalDateTime.now(), user2, schedule1_1);
        EventSignup registration2 =  new EventSignup(LocalDateTime.now().minusDays(3), user4, schedule1_1);
        EventSignup registration3 =  new EventSignup(LocalDateTime.now().plusDays(2), user3, schedule2);
        EventSignup registration4 =  new EventSignup(LocalDateTime.now().plusDays(1), user4, schedule2);
        EventSignup registration5 =  new EventSignup(LocalDateTime.now().minusDays(20), user1, schedule3);
        EventSignup registration6 =  new EventSignup(LocalDateTime.now().minusDays(15), user4, schedule3);
        eventSignupRepository.saveAll(List.of(registration1, registration2, registration3,
                registration4, registration5, registration6));

        JSONObject result = new JSONObject();
        result.put("winner", "Finn");
        result.put("grand prize", "Marceline");
        Result result_for_event2 = new Result(result.toString(), event2);
        resultRepository.save(result_for_event2);

        sportsEventRepository.delete(event1);

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}