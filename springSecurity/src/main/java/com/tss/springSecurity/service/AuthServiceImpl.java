package com.tss.springSecurity.service;

import com.tss.springSecurity.dto.LoginRequestDTO;
import com.tss.springSecurity.dto.RegistrationRequestDTO;
import com.tss.springSecurity.dto.UserResponseDTO;
import com.tss.springSecurity.entity.Role;
import com.tss.springSecurity.entity.User;
import com.tss.springSecurity.repository.RoleRepository;
import com.tss.springSecurity.repository.UserRepository;
import com.tss.springSecurity.security.JwtTokenProvider;
import com.tss.springSecurity.security.UserApiException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserResponseDTO register(RegistrationRequestDTO registrationRequestDTO) {
        if(userRepository.existsByUsername(registrationRequestDTO.getUsername())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST,"User already exists");
        }
        User user = new User();
        user.setUsername(registrationRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));
        Role UserRole = roleRepository.findByRoleName(registrationRequestDTO.getRoleName()).orElseThrow(()->new UserApiException(HttpStatus.BAD_REQUEST,"User not found"));
        user.setRole(UserRole);
        user = userRepository.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setId(user.getId());
        return userResponseDTO;
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtTokenProvider.generateToken(userDetails);

            return token;

        } catch (BadCredentialsException e) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Bad credentials");
        }
    }
}
