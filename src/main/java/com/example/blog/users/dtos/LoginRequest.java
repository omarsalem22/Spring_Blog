package com.example.blog.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    @Email(message = "Email Must Be valid")
    private String email;
    @NotBlank
    private String password;

}
