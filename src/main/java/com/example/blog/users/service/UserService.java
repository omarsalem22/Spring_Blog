package com.example.blog.users.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.users.dtos.LoginRequestDTO;
import com.example.blog.users.dtos.LoginResponseDTO;
import com.example.blog.users.dtos.UserRegistrationDto;
import com.example.blog.users.dtos.UserResponseDTO;
import com.example.blog.users.entities.User;
import com.example.blog.users.reposetories.UserRepository;
import com.example.blog.users.utils.JwtUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

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

    public LoginResponseDTO loginUser(LoginRequestDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(loginDTO.getEmail());
            User user = userRepository.findByEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            UserResponseDTO userResponseDTO = new UserResponseDTO(user);

            return new LoginResponseDTO(token, userResponseDTO);
        } catch (Exception e) {
            throw new RuntimeException("Invalid email or password", e);
        }
    }

}
