package com.tss.Collections.v1.Exceptions;

public class BookAlreadyBorrowed extends RuntimeException {
    public BookAlreadyBorrowed(String message) {
        super(message);
    }
}
