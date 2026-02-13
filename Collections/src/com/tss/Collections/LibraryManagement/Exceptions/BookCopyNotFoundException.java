package com.tss.Collections.LibraryManagement.Exceptions;

public class BookCopyNotFoundException extends RuntimeException {
    public BookCopyNotFoundException(String message) {
        super(message);
    }
}
