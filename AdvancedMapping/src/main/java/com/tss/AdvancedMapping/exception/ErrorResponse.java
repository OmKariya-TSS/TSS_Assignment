package com.tss.AdvancedMapping.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.Map;

@NoArgsConstructor
@Data
@ToString
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private Instant timestamp;
    private Map<String,String> error;

}
