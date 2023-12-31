package com.example.notificationorderapp.Channels;

import com.example.notificationorderapp.model.Notification;

public abstract class ChannelStrategy {
   public abstract void send(Notification notification);
    
}