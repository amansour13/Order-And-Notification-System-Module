package com.example.notificationorderapp.validation;

import jakarta.validation.constraints.*;

public class LoginValidator {

    @NotBlank(message = "The username is required.")
    @Size(min = 3, max = 20, message = "The username must be from 3 to 20 characters.")
    private String username;

    @NotBlank(message = "The password is required.")
    @Size(min = 6, max = 20)
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
