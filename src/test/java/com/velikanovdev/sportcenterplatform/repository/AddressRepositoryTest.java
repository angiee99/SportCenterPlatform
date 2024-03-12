package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.Address;
import com.velikanovdev.sportcenterplatform.job.SaveAddressJob;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private SaveAddressJob saveAddressJob;

    @Test
    public void insertAddress() {
        Address address = new Address(1L, "23456", "Palachova", "1", "Hradec");
        saveAddressJob.insertAddress(address);
    }
}