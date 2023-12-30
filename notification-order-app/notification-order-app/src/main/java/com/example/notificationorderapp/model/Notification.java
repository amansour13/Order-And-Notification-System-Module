package com.example.notificationorderapp.model;

import com.example.notificationorderapp.Channels.ChannelStrategy;
import com.example.notificationorderapp.model.Messages.MessageTemplate;

public class Notification {
    private ChannelStrategy channel;
    private MessageTemplate message;

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
