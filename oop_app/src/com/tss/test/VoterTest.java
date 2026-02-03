package com.tss.test;

import com.tss.Exception.AgeNotValidException;
import com.tss.model.Voter;

public class VoterTest {
    public static void main(String[] args) {
        try {
            Voter voter1 = new Voter(1, "Rahul", 20);
            System.out.println(voter1);
            Voter voter2 = new Voter(2, "Anita", 16);
            System.out.println(voter2);

        } catch (AgeNotValidException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}