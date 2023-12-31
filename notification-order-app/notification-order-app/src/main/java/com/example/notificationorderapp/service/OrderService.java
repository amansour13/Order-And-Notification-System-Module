package com.example.notificationorderapp.service;

import org.springframework.http.ResponseEntity;

import com.example.notificationorderapp.Channels.ChannelStrategy;
import com.example.notificationorderapp.Langauges.ILangauge;
import com.example.notificationorderapp.model.ComponentOrder;
import com.example.notificationorderapp.model.User;

public interface OrderService {
    
    public ResponseEntity<String> placeOrder(User user, ChannelStrategy channel, ILangauge langauge);
    
    public ResponseEntity<String> shipOrder(User user, ChannelStrategy channel, ILangauge langauge);
    
    public ResponseEntity<String> cancelOrder(User user, ChannelStrategy channel, ILangauge langauge, String orderId);

    public ComponentOrder getOrder(int id);

    public ResponseEntity<String> addProductToOrder(User user, String productID, int quantity, String username);
    public void createOrder(User user);

}