package com.tss.AccountManagement.v2.Exception;


public class InvalidNameException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Account holder name cannot be null or empty";
    }
}
