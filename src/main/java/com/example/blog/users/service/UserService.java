package com.example.blog.users.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.users.dtos.UserRegistrationDto;
import com.example.blog.users.dtos.UserResponseDTO;
import com.example.blog.users.entities.User;
import com.example.blog.users.reposetories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public UserResponseDTO registerUser(UserRegistrationDto registrationDto) {

        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Email is already used");

        }
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new IllegalArgumentException("UserName is already used");

        }
        // Map Dto to entity

        User user = User.builder()
                .username(registrationDto.getUsername())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .bio(registrationDto.getBio())
                .role(registrationDto.getRole() != null ? registrationDto.getRole() : "USER")
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser);

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
