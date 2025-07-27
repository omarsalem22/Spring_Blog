package com.example.blog.users.dtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;

}
