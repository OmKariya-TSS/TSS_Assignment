package com.tss.springSecurity.controller;

import com.tss.springSecurity.dto.LoginRequestDTO;
import com.tss.springSecurity.dto.RegistrationRequestDTO;
import com.tss.springSecurity.dto.UserResponseDTO;
import com.tss.springSecurity.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        UserResponseDTO user  = authService.register(registrationRequestDTO);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        String token = authService.login(loginRequestDTO);
        return  ResponseEntity.status(200).body(token);
    }

}
