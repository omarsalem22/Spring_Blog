package com.example.blog.users.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.users.dtos.LoginRequestDTO;
import com.example.blog.users.dtos.LoginResponseDTO;
import com.example.blog.users.dtos.UserRegistrationDto;
import com.example.blog.users.dtos.UserResponseDTO;
import com.example.blog.users.entities.User;
import com.example.blog.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/register")

    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegistrationDto registrationDto) {
        try {
            UserResponseDTO registerUser = userService.registerUser(registrationDto);
            return new ResponseEntity<>(registerUser, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO){
        try {
            LoginResponseDTO responseDTO=userService.loginUser(loginDTO);
            return ResponseEntity.ok(responseDTO);

            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity < List<User> > getAllUsers(){
        try {
            List<User> users =userService.getUsers();
            if (users.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(users);
        }
        catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
