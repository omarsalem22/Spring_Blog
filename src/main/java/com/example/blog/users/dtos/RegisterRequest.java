package com.example.blog.users.dtos;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;

    private String email;

    private String password;
    private String firstName;
    private String lastName;
    private String bio;

}
