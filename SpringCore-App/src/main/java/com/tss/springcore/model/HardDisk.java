package com.tss.springcore.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HardDisk {

    private int capacity;

    public HardDisk(@Value("256") int capacity) {
        System.out.println("inside parameterized constructor : hardDisk");
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "HardDisk{" +
                "capacity=" + capacity +
                '}';
    }
}