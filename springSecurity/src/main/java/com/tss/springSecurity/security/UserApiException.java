package com.tss.springSecurity.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
}
