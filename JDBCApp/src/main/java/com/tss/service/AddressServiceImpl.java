package com.tss.service;

import com.tss.entity.Address;
import com.tss.repository.AddressRepository;
import com.tss.repository.AddressRepositoryImpl;

import java.util.List;

public class AddressServiceImpl implements AddressService{
    private AddressRepository addressRepository = new AddressRepositoryImpl();
    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.readAddresses();
    }

    @Override
    public void addNewAddress(Address address) {
        addressRepository.addNewAddress(address);
    }
}
