package com.example.notificationorderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.service.UserService;
import com.example.notificationorderapp.service.UserServiceImpl;
import com.example.notificationorderapp.validation.AddBalanceValidator;
import com.example.notificationorderapp.validation.LoginValidator;
import com.example.notificationorderapp.validation.RegisterValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class UserController{

    @PostMapping("/register")
    public ResponseEntity<String> regsiter(@Valid @RequestBody RegisterValidator signUpRequest) {
        User user = new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            signUpRequest.getPassword(),
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginValidator loginRequest) {
        UserService userService = new UserServiceImpl();

        User user = userService.getUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/addbalance")
    public ResponseEntity<String> addBalance(@Valid @RequestBody AddBalanceValidator addBalanceRequest) {
        UserService userService = new UserServiceImpl();

        User user = userService.getUser(addBalanceRequest.getUsername(), addBalanceRequest.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }
        
        if (!userService.addBalance(user, addBalanceRequest.getAmount())) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);

    }
}