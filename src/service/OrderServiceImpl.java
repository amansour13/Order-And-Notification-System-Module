package src.service;

import src.model.ComponentOrder;
import src.model.Order;
import src.model.User;

import static src.util.Database.orders;
import static src.util.Database.products;
import static src.util.Database.users;
import static src.util.Database.stats;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OrderServiceImpl implements OrderService{

    @Override
    public void placeOrder(User user) {
        try {
            Order tempOrder = (Order) user.getOrder();
            if (!tempOrder.getStatus().equals("placed")){
                tempOrder.setStatus("placed");
                stats.placeTempCounter++;
            }
            // TODO: add to notifications Queue
        } catch (Exception e) {
            System.out.println("Exception in placeOrder as" + e.getMessage());
        }
    }

    @Override
    public void shipOrder(User user) {
       try {
            Order tempOrder = (Order) user.getOrder();
            if (!tempOrder.getStatus().equals("shipped")){
                tempOrder.setStatus("shipped");
                tempOrder.setTimeShip(LocalDate.now());
                orders.put(tempOrder.getID(), tempOrder);
                // TODO: add to notifications Queue
                
                // TODO: Deduct the fees and order price

                // if(tempOrder.getOrderType().equals("simple")){
                //     user.setBalance(user.getBalance());
                // }
                stats.shipTempCounter++;
            }
        } catch (Exception e) {
            System.out.println("Exception in shipOrder as" + e.getMessage());
        }
    }

    @Override
    public void cancelOrder(User user, String orderID) {
        try {
            Order userOrder = (Order) user.getOrder();
            // cacnel -> (placed)
            if (userOrder.getStatus().equals("placed"))
            {
                userOrder.setStatus("cancelled");
                orders.put(userOrder.getID(), userOrder);
                userOrder = new Order(orders.size(), "none", user.getUsername());
                stats.cancelTempCounter+=1;
            }
            
            // cancel -> (shipped)
            // Check if the difference is exactly one day
            long daysDifference = ChronoUnit.DAYS.between(LocalDate.now(), userOrder.getTimeShip());
            boolean checkTime = Math.abs(daysDifference) == 1;
            if (userOrder.getStatus().equals("shipped") && checkTime)
            {
                userOrder.setStatus("cancelled");
                orders.put(userOrder.getID(), userOrder);
                userOrder = new Order(orders.size(), "none", user.getUsername());
                stats.cancelTempCounter+=1;
            }

            // TODO: add to notifications Queue
        } catch (Exception e) {
            System.out.println("Exception in cancelOrder as" + e.getMessage());
        }
    }

    @Override
    public ComponentOrder getOrder(User user) {
        try {
            return user.getOrder();
        } catch (Exception e) {
            System.out.println("Exception in getOrder as" + e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean addProductToOrder(User user, String productID, int quantity, String username) {
        try {
            if (users.get(username) == null){
                return false;
            }
            if (products.get(productID) == null){
                return false;
            }
            if(products.get(productID).getStock() < quantity){
                return false;
            }
            
            Order userOrders = (Order) user.getOrder();
            for (ComponentOrder order : userOrders.getComponents()) {
                Order tempOrder = (Order) order; 
                if (tempOrder.getOwner().equals(username)){
                    tempOrder.addComponent(products.get(productID));
                    return true;
                }
            }

            // first time you order for specific username (yourself, others)
            Order currentOrder = new Order(orders.size(), (username.equals(user.getUsername()))?"simple":"compound", username);
            currentOrder.addComponent(products.get(productID));
            userOrders.addComponent(currentOrder);
            return true;

        } catch (Exception e) {
            System.out.println("Exception in addProductToOrder as" + e.getMessage());
            return false;
        }

    }

}
