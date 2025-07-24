package com.example.blog.users.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.users.User;
import com.example.blog.users.dtos.UserRegistrationDto;
import com.example.blog.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;

    }

    @PostMapping("/register")

    public ResponseEntity <User> register(@Valid @RequestBody UserRegistrationDto registrationDto){
        try {
            User registerUser=userService.registerUser(registrationDto);
            return new ResponseEntity<>(registerUser,HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
