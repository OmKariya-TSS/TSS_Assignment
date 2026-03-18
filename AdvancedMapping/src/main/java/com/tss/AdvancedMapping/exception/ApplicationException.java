package com.tss.AdvancedMapping.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public abstract class ApplicationException extends RuntimeException{
    private final String errorCode;
    private final HttpStatus errorStatus;
    public ApplicationException(String message,String errorCode,HttpStatus errorStatus){
        super(message);
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
    }
}
