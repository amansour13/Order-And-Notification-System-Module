package com.example.notificationorderapp.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationorderapp.model.Order;
import com.example.notificationorderapp.model.Product;
import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.service.OrderService;
import com.example.notificationorderapp.service.OrderServiceImpl;
import com.example.notificationorderapp.service.ProductService;
import com.example.notificationorderapp.service.ProductServiceImpl;
import com.example.notificationorderapp.service.UserService;
import com.example.notificationorderapp.service.UserServiceImpl;
import com.example.notificationorderapp.validation.AddProductValidator;
import com.example.notificationorderapp.validation.LoginValidator;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/add")
    public ResponseEntity<String> addProductToOrder(@Valid @RequestBody AddProductValidator addProductRequest) {
        OrderService orderService = new OrderServiceImpl();

        UserService userService = new UserServiceImpl();
        User user = userService.getUser(addProductRequest.getUsername(), addProductRequest.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Wrong information", HttpStatus.UNAUTHORIZED);
        }

        if (user.getOrder() == null) {
            orderService.createOrder(user);
        }

        String message = orderService.addProductToOrder(
            user, 
            addProductRequest.getProductId(),
            addProductRequest.getQuantity(),
            addProductRequest.getOwner());

        if (message == "success") {
            message = "Added Successfully Order id: " + ((Order)user.getOrder()).getID();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(message, HttpStatus.CONFLICT);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {
        OrderService orderService = new OrderServiceImpl();
        Order order = (Order)orderService.getOrder(id);
        
        if (order == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println(order.toString());

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody LoginValidator loginValidator) {
        OrderService orderService = new OrderServiceImpl();

        UserService userService = new UserServiceImpl();
        User user = userService.getUser(loginValidator.getUsername(), loginValidator.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Wrong information", HttpStatus.UNAUTHORIZED);
        }

        if (user.getOrder() == null) {
            return new ResponseEntity<>("No order found", HttpStatus.NOT_FOUND);
        }
        // TODO: is this the best way to use sms channel ???
        if (orderService.placeOrder(user, "com.example.notificationorderapp.Channels.SMS")) {
            return new ResponseEntity<>("Order Placed Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Order Failed", HttpStatus.CONFLICT);
    }


    @PostMapping("/ship")
    public ResponseEntity<String> shiporder(@RequestBody LoginValidator loginValidator) {
        OrderService orderService = new OrderServiceImpl();
        
        UserService userService = new UserServiceImpl();
        User user = userService.getUser(loginValidator.getUsername(), loginValidator.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Wrong information", HttpStatus.UNAUTHORIZED);
        }
        
        if (user.getOrder() == null) {
            return new ResponseEntity<>("No order found", HttpStatus.NOT_FOUND);
        }
        
        System.out.println(user.getBalance());
        // TODO: is this the best way to use sms channel ???
        String result = orderService.shipOrder(user, "com.example.notificationorderapp.Channels.SMS");
        System.out.println(user.getBalance());
        if (result == "success") {
            return new ResponseEntity<>("Order is shipped", HttpStatus.OK);
        }

        return new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }

        @PostMapping("/cancel")
    public ResponseEntity<String> cancelorder(@RequestBody LoginValidator loginValidator) {
        OrderService orderService = new OrderServiceImpl();
        
        UserService userService = new UserServiceImpl();
        User user = userService.getUser(loginValidator.getUsername(), loginValidator.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Wrong information", HttpStatus.UNAUTHORIZED);
        }
        
        if (user.getOrder() == null) {
            return new ResponseEntity<>("No order found", HttpStatus.NOT_FOUND);
        }
        
        System.out.println(user.getBalance());
        // TODO: is this the best way to use sms channel ???
        String result = orderService.cancelOrder(user, "com.example.notificationorderapp.Channels.SMS");
        System.out.println(user.getBalance());
        if (result == "success") {
            return new ResponseEntity<>("Order is cancelled", HttpStatus.OK);
        }

        return new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }
    
}