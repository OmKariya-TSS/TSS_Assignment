package com.tss.Creational.prototype.model;

import java.util.List;

public class Company {
    String companyName;
    List<Employee> employees ;
    public Company(String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company shallowClone(){
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", employees=" + employees +
                '}';
    }

    public Company deepClone(){
        return new Company(this.companyName,this.employees);
    }
}
