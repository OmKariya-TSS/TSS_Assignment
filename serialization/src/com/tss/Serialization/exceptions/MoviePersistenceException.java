package com.tss.Serialization.exceptions;

public class MoviePersistenceException extends RuntimeException {
    //in case of serialization failure , this exception is added
    public MoviePersistenceException(String message) {
        super(message);
    }
}
