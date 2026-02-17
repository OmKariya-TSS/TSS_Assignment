package com.tss.Behavioral.Strategy.test;

import com.tss.Behavioral.Strategy.model.*;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Om", new JuniorRole(), new DeveloperResponsibility());
        Employee e2 = new Employee(2,"Riya",new SeniorRole(),new HRResponsibility());
        e1.work();
        e2.work();
        System.out.println("\nAfter Promotion:");
        e1.setRole(new ExpertRole());
        e1.work();
    }
}
