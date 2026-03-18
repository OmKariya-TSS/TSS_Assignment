package com.tss.service;

import com.tss.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();
    void addNewAddress(Address address);
}
