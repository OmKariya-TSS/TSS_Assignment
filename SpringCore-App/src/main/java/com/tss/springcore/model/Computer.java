package com.tss.springcore.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Computer {

    private String name;
    private HardDisk hardDisk;

    public Computer(@Value("Apple") String name, HardDisk hardDisk) {
        System.out.println("inside parameterized constructor computer");
        this.name = name;
        this.hardDisk = hardDisk;
    }

    public String getName() {
        return name;
    }

    public HardDisk getHardDisk() {
        return hardDisk;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", hardDisk=" + hardDisk +
                '}';
    }
}