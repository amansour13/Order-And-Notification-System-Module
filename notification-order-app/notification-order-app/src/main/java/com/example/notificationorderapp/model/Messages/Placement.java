package com.example.notificationorderapp.model.Messages;
import com.example.notificationorderapp.model.ComponentOrder;
import com.example.notificationorderapp.model.Order;
import com.example.notificationorderapp.model.User;


public class Placement extends MessageTemplate {
    @Override
    public void createMessage(ComponentOrder order, User user) {
        String conString = String.format("\n[\nDear %s , your booking of the \n%sis confirmed. thanks for using our store :)\n]\n", user.getUsername(), ((Order)order).toString());
      
        setContent(conString);

    };

}
