package com.example.blog.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
  @NotBlank(message="user name is reqired")
  @Size (min =3 , max= 50 , message= "User Name must be between 3 ,50 ")
  private String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be valid")
  private String email;


  @NotBlank(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters")
   private String password;

   private String firstName;
    private String lastName;

    private String bio;

    @NotBlank(message = "Role is required")
    private String role;


}
