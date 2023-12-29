package src.Channels;

import src.model.User;

public class Email extends ChannelStrategy {
    @Override
    void send(User user) {
        System.out.println("send to email "+user.getEmail());
        
    }    
}
