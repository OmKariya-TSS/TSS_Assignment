package com.tss.controller;

import com.tss.entity.Address;
import com.tss.service.AddressService;
import com.tss.service.AddressServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AddressController {
    private AddressService addressService = new AddressServiceImpl();
    public void readAllAddresses(){
        List<Address> list = addressService.getAllAddresses();
        for(Address address : list){
            System.out.println(address);
        }
    }
    public void addNewAddress(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter address id :");
        int address_id = scanner.nextInt();
        System.out.println("enter city :");
        String city = scanner.next();
        System.out.println("enter state : ");
        String state = scanner.next();
        System.out.println("enter pincode :");
        String pinCode = scanner.next();
        System.out.println("enter student id :");
        int student_id = scanner.nextInt();
        addressService.addNewAddress(new Address(address_id,city,state,pinCode,student_id));
    }
}
