package com.example.blog.users.dtos;

import com.example.blog.users.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserResponseDTO {
    private int  id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String role;

public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.bio = user.getBio();
        this.role = user.getRole();
    }
}
