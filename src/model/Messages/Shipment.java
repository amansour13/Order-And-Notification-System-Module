package src.model.Messages;

import src.model.User;
import src.model.ComponentOrder;



public class Shipment extends MessageTemplate {
    @Override
    public void createMessage(ComponentOrder order, User user) {
        String conString= String.format("Dear %s , your booking of the  %s is shipped. thanks for using our store :)", user.getUsername(), order.toString());
        setContent(conString);
    };    
}
