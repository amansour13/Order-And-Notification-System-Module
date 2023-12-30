package com.example.notificationorderapp.service;

import com.example.notificationorderapp.model.ComponentOrder;
import com.example.notificationorderapp.model.User;

public interface OrderService {
    
    public boolean placeOrder(User user,String className);
    
    public String shipOrder(User user,String className);
    
    public void cancelOrder(User user, String orderID,String className);

    public ComponentOrder getOrder(int id);

    public String addProductToOrder(User user, String productID, int quantity, String username);
    public void createOrder(User user);

}