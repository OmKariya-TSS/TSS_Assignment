package com.tss.LibraryManagement.Exceptions;

public class BookAlreadyBorrowed extends RuntimeException {
    public BookAlreadyBorrowed(String message) {
        super(message);
    }
}
