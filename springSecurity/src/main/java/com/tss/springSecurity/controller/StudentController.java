package com.tss.springSecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studentapp")
@RequiredArgsConstructor
public class StudentController {
    @GetMapping("/hiAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> hiAdmin() {
        return ResponseEntity.ok("hi admin");
    }

    @GetMapping("/hiCustomer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> hiCustomer() {
        return ResponseEntity.ok("hi customer");
    }
}
