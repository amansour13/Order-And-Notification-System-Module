package com.example.notificationorderapp.Channels;

import static com.example.notificationorderapp.util.Database.stats;

import com.example.notificationorderapp.model.Notification;
import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.util.Statistics.Pair;
public class Email extends ChannelStrategy {
    @Override
   public void send(Notification notification) {
        User user = notification.getUser();

        System.out.println();
        System.out.println("Sending Email to " + user.getEmail());
        System.out.println(notification.getMessage().getContent());

        boolean found = false;
        for (Pair pair : stats.emailsCounter) {
            if (pair.getKey().equals(user.getEmail())) {
                // If the key is found, create a new Pair with the updated value
                pair.setValue(pair.getValue()+1);
                found = true;
                break;
            }
        }

        if (!found) {
            // If the key is not found, add a new pair to the queue
            
            stats.emailsCounter.add(stats.new Pair(user.getEmail(), 1));

        }
    }
}
