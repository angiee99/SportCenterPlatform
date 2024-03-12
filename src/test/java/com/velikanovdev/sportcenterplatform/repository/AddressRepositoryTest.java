package com.velikanovdev.sportcenterplatform.repository;

import com.velikanovdev.sportcenterplatform.entity.Address;
import com.velikanovdev.sportcenterplatform.job.SaveAddressJob;
import com.velikanovdev.sportcenterplatform.repository.configuration.AddressRepositoryTestConfiguration;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AddressRepositoryTestConfiguration.class)
public class AddressRepositoryTest {
    @Resource
    private SaveAddressJob saveAddressJob;

    @Test
    public void insertAddress() {
        Address address = new Address(1L, "23456", "Palachova", "1", "Hradec");
        saveAddressJob.insertAddress(address);
    }
}
