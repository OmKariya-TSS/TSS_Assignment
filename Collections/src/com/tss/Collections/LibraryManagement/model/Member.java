package com.tss.Collections.LibraryManagement.model;

import java.util.HashSet;
import java.util.Set;

public class Member {

    private static int idCounter = 1;
    private final int id;
    private String name;
    private final String email;
    public Member(String name, String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.id = idCounter++;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format(
                """
                -------------------------
                Member ID : %d
                Name      : %s
                Email     : %s
                -------------------------
                """,
                id, name, email
        );
    }

}
