package com.kavya.digitalbanking.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "firstname is required")
    private String firstname;

    @NotBlank(message = "Password is required")
    private String password;

    @Email
    @NotBlank(message = "Email is required")
    private String email;
}
