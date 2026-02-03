package com.tss.Exception;

public class AgeNotValidException extends Exception {
    private int age;

    public AgeNotValidException(int age) {
        this.age = age;
    }

    @Override
    public String getMessage() {
        return "Age " + age + " is not valid. Voter must be 18 or older.";
    }
}
