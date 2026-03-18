package com.tss.service;

import com.tss.entity.Customer;
import com.tss.repository.CustomerRepository;
import com.tss.repository.CustomerRepositoryImpl;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepo = new CustomerRepositoryImpl();
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.readCustomers();
    }

    @Override
    public void addNewCustomer(Customer customer) {
        customerRepo.addNewCustomer(customer);
    }
}
