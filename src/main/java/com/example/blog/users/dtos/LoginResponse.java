package com.example.blog.users.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
    private String email;
}
