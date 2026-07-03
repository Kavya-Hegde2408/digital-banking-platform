package com.kavya.digitalbanking.auth.service;

import com.kavya.digitalbanking.auth.dto.RegisterRequest;
import com.kavya.digitalbanking.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
}
