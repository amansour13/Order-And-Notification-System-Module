package src.Channels;

import src.model.User;

public class SMS extends ChannelStrategy {
    @Override
    void send(User user) {
        System.out.println("send to phone "+user.getPhone());
    }    
}
