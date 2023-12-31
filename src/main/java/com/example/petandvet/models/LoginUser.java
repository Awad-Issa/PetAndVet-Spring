package com.example.petandvet.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUser {

  @NotEmpty(message = "Email is required!")
  @Email(message = "Please enter a valid email!")
  private String email;

  @NotEmpty(message = "Password is required!")
  @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
  private String password;

}