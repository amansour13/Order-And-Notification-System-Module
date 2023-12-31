package com.example.notificationorderapp.validation;

import jakarta.validation.constraints.*;

public class AddProductValidator {

    @NotBlank(message = "The username is required.")
    @Size(min = 3, max = 20, message = "The username must be from 3 to 20 characters.")
    private String username;

    @NotBlank(message = "The password is required.")
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank(message = "The product is required.")
    private String productId;

    @NotNull(message = "The quantity is required.")
    private int quantity;

    @NotBlank(message = "The owner is required.")
    private String owner;

    public String getUsername() {
      return username;
    }

    public String getPassword() {
      return password;
    }

    public String getProductId() {
      return productId;
    }

    public int getQuantity() {
      return quantity;
    }

    public String getOwner() {
      return owner;
    }
}
