package com.example.notificationorderapp.Channels;

import com.example.notificationorderapp.model.User;

public abstract class ChannelStrategy {
   public abstract void send(User user);
    
}