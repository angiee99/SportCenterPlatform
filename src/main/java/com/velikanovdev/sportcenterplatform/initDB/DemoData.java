package com.velikanovdev.sportcenterplatform.initDB;

import com.velikanovdev.sportcenterplatform.entity.Address;
import com.velikanovdev.sportcenterplatform.repository.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoData implements ApplicationRunner {
    private final AddressesRepository addressesRepository;
    @Autowired
    public DemoData(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        addressesRepository.save(new Address(null, "23456", "Palachova", "1", "Hradec"));
    }
}