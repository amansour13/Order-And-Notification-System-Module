package com.example.notificationorderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.service.UserService;
import com.example.notificationorderapp.service.UserServiceImpl;
import com.example.notificationorderapp.validation.RegisterValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class Controller{

    @PostMapping("/register")
    public ResponseEntity<String> postMethodName(@Valid @RequestBody RegisterValidator signUpRequest) {
        User user = new User(
            signUpRequest.getUsername(),
            signUpRequest.getPassword(),
            signUpRequest.getEmail(),
            signUpRequest.getPhone(),
            signUpRequest.getLocation(),
            signUpRequest.getBalance()
        );

        UserService UserService = new UserServiceImpl();
        if (UserService.addUser(user)) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Faild, user already exsists", HttpStatus.CONFLICT);
        }

    }
}