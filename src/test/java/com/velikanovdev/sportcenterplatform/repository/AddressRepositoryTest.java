package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.Address;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressesRepository addressesRepository;

    @Test
    @Transactional
    public void insertAddress() {
        Address address = new Address(null, "23456", "Palachova", "1", "Hradec");
        addressesRepository.save(address);
    }
}