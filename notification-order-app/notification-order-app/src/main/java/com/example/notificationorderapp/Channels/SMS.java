package com.example.notificationorderapp.Channels;

import static com.example.notificationorderapp.util.Database.stats;

import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.util.Statistics.Pair;

public class SMS extends ChannelStrategy {
    @Override
   public void send(User user) {
         boolean found = false;

        for (Pair pair : stats.phonesCounter) {
            if (pair.getKey().equals(user.getPhone())) {
                // If the key is found, create a new Pair with the updated value
                pair.setValue(pair.getValue()+1);
                found = true;
                break;
            }
        }

        if (!found) {
            // If the key is not found, add a new pair to the queue
            
            stats.phonesCounter.add(stats.new Pair(user.getPhone(), 1));

        }
    }    
}
