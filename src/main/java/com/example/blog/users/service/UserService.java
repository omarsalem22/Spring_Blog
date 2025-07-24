package com.example.blog.users.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.users.User;
import com.example.blog.users.dtos.UserRegistrationDto;
import com.example.blog.users.reposetories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public User registerUser(UserRegistrationDto registrationDto) {

        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Email is already used");

        }
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new IllegalArgumentException("UserName is already used");

        }
        // Map Dto to entity

        User user=User.builder()
        .username(registrationDto.getUsername())
        .email(registrationDto.getEmail())
        .password(passwordEncoder.encode(registrationDto.getPassword()))
        .firstName(registrationDto.getFirstName())
        .lastName(registrationDto.getLastName())
        .bio(registrationDto.getBio()).
        role(registrationDto.getRole())
        .build();

        return userRepository.save(user);


    }

}
