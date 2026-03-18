package com.tss.repository;

import com.tss.entity.Address;
import java.util.List;

public interface AddressRepository {
    List<Address> readAddresses();
    void addNewAddress(Address address);
}
