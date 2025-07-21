package com.example.blog.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoginResponse {
    private String token;
    private String username;
    private String email;
}
