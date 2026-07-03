package com.kavya.digitalbanking.auth.service;

import com.kavya.digitalbanking.auth.dto.RegisterRequest;
import com.kavya.digitalbanking.auth.dto.RegisterResponse;
import com.kavya.digitalbanking.auth.entity.User;
import com.kavya.digitalbanking.auth.enums.Role;
import com.kavya.digitalbanking.exception.ResourceAlreadyExistsException;
import com.kavya.digitalbanking.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request){

        if(userRepository.existsByUsername(request.getUsername())){
            throw new ResourceAlreadyExistsException("Username already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new ResourceAlreadyExistsException("Email already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);

       return RegisterResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();

    }

}