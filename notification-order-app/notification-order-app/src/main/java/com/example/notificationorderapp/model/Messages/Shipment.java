package com.example.notificationorderapp.model.Messages;

import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.model.ComponentOrder;
import com.example.notificationorderapp.model.Order;



public class Shipment extends MessageTemplate {
    @Override
    public void createMessage(ComponentOrder order, User user) {
        String conString= String.format("\n[\nDear %s , your booking of the  %sis shipped. thanks for using our store :)\n]\n", user.getUsername(), ((Order)order).toString());
        setContent(conString);
    };    
}
