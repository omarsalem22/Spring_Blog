package com.example.blog.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(nullable = false)

    @Size(min = 3, max = 50)
    private String username;
    private String firstName;
    private String lastName;

    @Column(nullable = false)
    @Email(message = "Email must be valid" )
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(columnDefinition = "TEXT")
    private String bio;

}
