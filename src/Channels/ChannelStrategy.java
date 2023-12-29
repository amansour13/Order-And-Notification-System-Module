package src.Channels;

import src.model.User;

public abstract class ChannelStrategy {
    abstract void send(User user);
    
}