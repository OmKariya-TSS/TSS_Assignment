package com.tss.Creational.prototype.test;

import com.tss.Creational.prototype.model.Company;
import com.tss.Creational.prototype.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class ProtoTest {
    public static void main(String[] args) {
        Employee e1  = new Employee("OM");
        Employee e2 = new Employee("abcd");
        Employee e3 = e2.clone();
        e3.setName("modified");
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println("companies");
        List<Employee> l1 = new ArrayList<>();
        l1.add(e1);
        List<Employee> l2 = new ArrayList<>();
        l2.add(e1);
        l2.add(e2);
        Company company1 = new Company("TSS",l1);
        Company company2 = new Company("Swabhav",l2);
        Company company3 = company2.shallowClone();
        company3.setEmployees(l1);
        System.out.println(company1);
        System.out.println(company2);
        System.out.println("shallow copy");
        System.out.println(company3);
        Company company4 = company1.deepClone();
        company4.setEmployees(l2);
        System.out.println("deep copy");
        System.out.println(company4);
    }
}
