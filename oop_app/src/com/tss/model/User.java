package com.tss.model;

import java.util.Comparator;

public class User implements Comparator<User>{

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static int compareByName(User u1, User u2) {
        return u1.name.compareTo(u2.name);
    }

    @Override
    public int compare(User o1, User o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
