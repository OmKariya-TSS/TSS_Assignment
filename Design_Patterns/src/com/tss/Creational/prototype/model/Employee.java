package com.tss.Creational.prototype.model;

public class Employee {
    String name;

    public Employee(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
    @Override
    public Employee clone(){
        return new Employee(this.name);
    }
}
