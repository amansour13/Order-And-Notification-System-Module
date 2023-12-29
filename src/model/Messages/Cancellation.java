package src.model.Messages;

import src.model.ComponentOrder;
import src.model.Order;
import src.model.User;

public class Cancellation extends MessageTemplate{


    @Override
    public void createMessage(ComponentOrder order, User user) {
        String conString= String.format("Dear %s , your booking of the  %s is cancelled. thanks for using our store :)", user.getUsername(), ((Order)order).getComponents().toString());
        setContent(conString);
    }    
}
