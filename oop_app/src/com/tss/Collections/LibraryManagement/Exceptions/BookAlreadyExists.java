package com.tss.Collections.LibraryManagement.Exceptions;

public class BookAlreadyExists extends RuntimeException {
    public BookAlreadyExists(String message) {
        super(message);
    }
}
