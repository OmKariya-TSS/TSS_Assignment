package com.tss.Behavioral.Strategy.model;
public class Employee {

    private int id;
    private String name;

    private Role role;
    private Responsibility responsibility;

    public Employee(int id, String name, Role role, Responsibility responsibility) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.responsibility = responsibility;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setResponsibility(Responsibility responsibility) {
        this.responsibility = responsibility;
    }

    public void work() {
        System.out.print("Employee: " + name + " → ");
        role.applyRole();
        responsibility.performResponsibility();
    }
}
