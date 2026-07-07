package com.kavya.digitalbanking.auth.service;

import com.kavya.digitalbanking.auth.dto.LoginRequest;
import com.kavya.digitalbanking.auth.dto.LoginResponse;
import com.kavya.digitalbanking.auth.dto.RegisterRequest;
import com.kavya.digitalbanking.auth.dto.RegisterResponse;
import com.kavya.digitalbanking.auth.entity.User;
import com.kavya.digitalbanking.auth.enums.Role;
import com.kavya.digitalbanking.exception.ResourceAlreadyExistsException;
import com.kavya.digitalbanking.auth.repository.UserRepository;
import com.kavya.digitalbanking.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        log.info("Registration request received for email: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            log.warn("Registration failed. Email already exists: {}", request.getEmail());
            throw new ResourceAlreadyExistsException("Email already exists");
        }
        User user = User.builder()
                .firstname(request.getFirstname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);
        log.info("User registered successfully. UserId={}, Email={}",
                savedUser.getId(),
                savedUser.getEmail());

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .firstname(request.getFirstname())
                .email(savedUser.getEmail())
                .build();

    }

    @Override
    public LoginResponse login(LoginRequest request) {

        log.info("Login request received for email: {}", request.getEmail());

        Authentication authentication =
                authenticationManager.authenticate(

                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        log.info("User authenticated successfully: {}",
                authentication.getName());

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String jwtToken =
                jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .token(jwtToken)
                .tokenType("Bearer")
                .message("Login Successful")
                .build();
    }
}