package com.tss.controller;

import com.tss.entity.Customer;
import com.tss.service.CustomerService;
import com.tss.service.CustomerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();
    public void readAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        for(Customer customer : customers){
            System.out.println(customer);
        }
    }
    public void addCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter customer id :");
        int customer_id = scanner.nextInt();
        System.out.println("enter email :");
        String email =  scanner.next();
        System.out.println("enter password :");
        String password  = scanner.next();
        System.out.println("enter phone :");
        String phone = scanner.next();
        System.out.println("enter address :");
        String address = scanner.next();
        System.out.println("enter name :");
        String name = scanner.next();
        customerService.addNewCustomer(new Customer(customer_id,name,email,password,phone,address));
    }
}
