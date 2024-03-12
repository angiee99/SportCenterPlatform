package com.velikanovdev.sportcenterplatform.job;

import com.velikanovdev.sportcenterplatform.entity.Address;
import com.velikanovdev.sportcenterplatform.repository.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SaveAddressJob {
    public static final String NAME = "SaveAddressJob";
    private AddressesRepository addressesRepository;
    @Autowired
    public void setAddressesRepository(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    public void insertAddress(Address address) {
//        Address address = new Address(1L, "23456", "Palachova", "1", "Hradec");
        addressesRepository.saveAndFlush(address);
    }
}
