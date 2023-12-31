package com.example.notificationorderapp.model;

import java.util.Timer;
import java.util.TimerTask;

import com.example.notificationorderapp.Channels.ChannelStrategy;
import com.example.notificationorderapp.model.Messages.MessageTemplate;
import com.example.notificationorderapp.util.Database;


public class Notification {
    private ChannelStrategy channel;
    private MessageTemplate message;
    private User user;
    static final int DEFAULT_WAIT = 10000; 
    
    static class Notify extends TimerTask {
        public void run() {
            if (!Database.notificationsQueue.isEmpty()) {
                Notification notification = Database.notificationsQueue.remove();
                notification.getChannel().send(notification);
            }
        }
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public static void waitAndSendNotifications() {
        Timer timer = new Timer();
        timer.schedule(new Notify(), 0, DEFAULT_WAIT);
    }

    public MessageTemplate getMessage() {
        return message;
    }
    public void setMessage(MessageTemplate message) {
        this.message = message;
    }
    public ChannelStrategy getChannel() {
        return channel;
    }
    public void setChannel(ChannelStrategy channel) {
        this.channel = channel;
    }
    
    
}
