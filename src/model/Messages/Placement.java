package src.model.Messages;
import src.model.ComponentOrder;
import src.model.Order;
import src.model.User;


public class Placement extends MessageTemplate {
    @Override
    public void createMessage(ComponentOrder order, User user) {
        String conString = String.format("\n[\nDear %s , your booking of the \n%sis confirmed. thanks for using our store :)\n]\n", user.getUsername(), ((Order)order).toString());
      
        setContent(conString);

    };

}
