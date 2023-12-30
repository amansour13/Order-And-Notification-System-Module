package com.example.notificationorderapp.validation;

import jakarta.validation.constraints.*;

public class RegisterValidator {

  @NotBlank(message = "The username is required.")
  @Size(min = 3, max = 20, message = "The username must be from 3 to 20 characters.")
  private String username;

  @NotEmpty(message = "The email is required.")
  @Email(message = "The email is not a valid email.")
  private String email;

  @NotBlank(message = "The password is required.")
  @Size(min = 6, max = 20)
  private String password;

  @NotNull(message = "The phone is required.")
  private String phone;

  
  @NotNull(message = "The location is required.")
  private String location;

  @NotNull(message = "The balance is required.")
  private double balance;
  
  
  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

}
