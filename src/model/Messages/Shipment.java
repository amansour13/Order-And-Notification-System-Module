package src.model.Messages;

import src.model.User;
import src.model.ComponentOrder;
import src.model.Order;



public class Shipment extends MessageTemplate {
    @Override
    public void createMessage(ComponentOrder order, User user) {
        String conString= String.format("Dear %s , your booking of the  %s is shipped. thanks for using our store :)", user.getUsername(), ((Order)order).getComponents().toString());
        setContent(conString);
    };    
}
