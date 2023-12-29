package src.model.Messages;

import java.util.List;

import src.model.ComponentOrder;
import src.model.User;

public class Cancellation extends MessageTemplate{


    @Override
    public String createMessage(ComponentOrder order, User user, List<User> compoundUsers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMessage'");
    }    
}
