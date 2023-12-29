package src.service;

import src.model.ComponentOrder;
import src.model.User;

public interface OrderService {
    
    public void placeOrder(User user,String className);
    
    public void shipOrder(User user,String className);
    
    public void cancelOrder(User user, String orderID,String className);

    public ComponentOrder getOrder(User user);

    public Boolean addProductToOrder(User user, String productID, int quantity, String username);

}