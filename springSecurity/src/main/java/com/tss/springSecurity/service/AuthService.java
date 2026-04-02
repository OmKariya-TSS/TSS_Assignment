package com.tss.springSecurity.service;

import com.tss.springSecurity.dto.LoginRequestDTO;
import com.tss.springSecurity.dto.RegistrationRequestDTO;
import com.tss.springSecurity.dto.UserResponseDTO;

public interface AuthService{
    UserResponseDTO register(RegistrationRequestDTO registrationRequestDTO);
    String login(LoginRequestDTO loginRequestDTO);
}