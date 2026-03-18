package com.tss.repository;

import com.tss.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> readCustomers();
    void addNewCustomer(Customer customer);
}
