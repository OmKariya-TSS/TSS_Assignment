package com.tss.hibernate_demo.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String resource,Object identifier) {
        super(resource+" not found "+identifier,"RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
