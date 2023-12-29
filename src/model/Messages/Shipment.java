package src.model.Messages;

import src.model.User;
import src.model.ComponentOrder;
import java.util.List;



public class Shipment extends MessageTemplate {
    @Override
    public String createMessage(ComponentOrder order, User user, List<User> compoundUsers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMessage'");
    };    
}
