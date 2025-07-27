package com.example.blog.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class LoginResponseDTO {
    private String token;
    private UserResponseDTO user;

    public LoginResponseDTO(String token, UserResponseDTO user) {
        this.token = token;
        this.user = user;
    }

}
