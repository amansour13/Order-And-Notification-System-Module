package com.example.notificationorderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationorderapp.Channels.ChannelStrategy;
import com.example.notificationorderapp.model.Order;
import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.service.OrderService;
import com.example.notificationorderapp.service.OrderServiceImpl;
import com.example.notificationorderapp.service.UserService;
import com.example.notificationorderapp.service.UserServiceImpl;
import com.example.notificationorderapp.validation.AddProductValidator;
import com.example.notificationorderapp.validation.NotificationValidator;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService = new OrderServiceImpl();

    private UserService userService = new UserServiceImpl();

    @PostMapping("/add")
    public ResponseEntity<String> addProductToOrder(@Valid @RequestBody AddProductValidator addProductRequest) {
        User user = userService.getUser(addProductRequest.getUsername(), addProductRequest.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }

        if (user.getOrder() == null) {
            orderService.createOrder(user);
        }

        return orderService.addProductToOrder(
            user, 
            addProductRequest.getProductId(),
            addProductRequest.getQuantity(),
            addProductRequest.getOwner());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {
        
        Order order = (Order)orderService.getOrder(id);
        
        if (order == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@Valid @RequestBody NotificationValidator notificationValidator) {        
        User user = userService.getUser(notificationValidator.getUsername(), notificationValidator.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }

        if (user.getOrder() == null) {
            return new ResponseEntity<>("No order found", HttpStatus.NOT_FOUND);
        }

        ChannelStrategy channelStrategy = notificationValidator.getNotficationChannel();
        if (channelStrategy == null) {
            return new ResponseEntity<>("Notifcation Channel not specified or not supported", HttpStatus.BAD_REQUEST);
        }

        return orderService.placeOrder(user, channelStrategy);
    }


    @PostMapping("/ship")
    public ResponseEntity<String> shiporder(@Valid @RequestBody NotificationValidator notificationValidator ) {
        User user = userService.getUser(notificationValidator.getUsername(), notificationValidator.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }
        
        if (user.getOrder() == null) {
            return new ResponseEntity<>("No order found", HttpStatus.NOT_FOUND);
        }
        
        ChannelStrategy channelStrategy = notificationValidator.getNotficationChannel();
        if (channelStrategy == null) {
            return new ResponseEntity<>("Notifcation Channel not specified or not supported", HttpStatus.BAD_REQUEST);
        }

        return orderService.shipOrder(user, channelStrategy);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancelorder(@Valid @RequestBody NotificationValidator notificationValidator, @PathVariable String id) {
        User user = userService.getUser(notificationValidator.getUsername(), notificationValidator.getPassword());
        if (user == null) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }
        
        ChannelStrategy channelStrategy = notificationValidator.getNotficationChannel();
        if (channelStrategy == null) {
            return new ResponseEntity<>("Notifcation Channel not specified or not supported", HttpStatus.BAD_REQUEST);
        }

        return orderService.cancelOrder(user, channelStrategy, id);
    }
    
}