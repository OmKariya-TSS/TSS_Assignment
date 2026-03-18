package com.tss.service;

import com.tss.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    void addNewCustomer(Customer customer);
}
