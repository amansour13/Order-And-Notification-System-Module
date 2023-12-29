package src.service;

import src.Channels.ChannelStrategy;
import src.model.ComponentOrder;
import src.model.Order;
import src.model.User;
import src.model.Messages.Cancellation;
import src.model.Messages.MessageTemplate;
import src.model.Messages.Placement;
import src.model.Messages.Shipment;
import src.model.Notification;

import static src.util.Database.orders;
import static src.util.Database.products;
import static src.util.Database.users;
import static src.util.Database.stats;
import static src.util.Database.notificationsQueue;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OrderServiceImpl implements OrderService{
    

    @Override
    public void placeOrder(User user,String className) {
        try {
            Order tempOrder = (Order) user.getOrder();
            if (!tempOrder.getStatus().equals("placed")){
                tempOrder.setStatus("placed");
                
                Notification notification = new Notification();
                MessageTemplate message = new Placement();
                Class<?> clazz = Class.forName(className); //TODO: not experimented yet 
                Object channel =  clazz.getDeclaredConstructor().newInstance();
                message.createMessage(tempOrder, user);
                notification.setMessage(message);
                notification.setChannel((ChannelStrategy)channel);
                
                notificationsQueue.add(notification);
                stats.placeTempCounter++;
            }
            
        } catch (Exception e) {
            System.out.println("Exception in placeOrder as" + e.getMessage());
        }
    }

    @Override
    public void shipOrder(User user, String className) {
       try {
            Order tempOrder = (Order) user.getOrder();
            if (!tempOrder.getStatus().equals("shipped")){
                tempOrder.setStatus("shipped");
                tempOrder.setTimeShip(LocalDate.now());
                orders.put(tempOrder.getID(), tempOrder);
                
                Notification notification = new Notification();
                MessageTemplate message = new Shipment();
                Class<?> clazz = Class.forName(className); //TODO: not experimented yet 
                Object channel =  clazz.getDeclaredConstructor().newInstance();
                message.createMessage(tempOrder, user);
                notification.setMessage(message);
                notification.setChannel((ChannelStrategy)channel);
                notificationsQueue.add(notification);
                
                // Deduct the fees and order price from the simple order
                if(tempOrder.getOrderType().equals("simple") && user.getBalance() >= (tempOrder.getTotalPrice() + tempOrder.getShippingFees())){
                    user.setBalance(user.getBalance() - (tempOrder.getTotalPrice() + tempOrder.getShippingFees()));
                }

                // TODO : Deduct the fees and order price in case of compound orders
                //user.setBalance(user.getBalance() - tempOrder.getTotalPrice());
                    
                    
                stats.shipTempCounter++;
            }
        } catch (Exception e) {
            System.out.println("Exception in shipOrder as" + e.getMessage());
        }
    }

    @Override
    public void cancelOrder(User user, String orderID,String className) {
        try {
            Order userOrder = (Order) user.getOrder();
            // cacnel -> (placed)
            if (userOrder.getStatus().equals("placed"))
            {
                sendCancelNotify(user, orderID, className);
            }

            // cancel -> (shipped)
            // Check if the difference is exactly one day
            long daysDifference = ChronoUnit.DAYS.between(LocalDate.now(), userOrder.getTimeShip());
            boolean checkTime = Math.abs(daysDifference) == 1;
            if (userOrder.getStatus().equals("shipped") && checkTime)
            {
                sendCancelNotify(user, orderID, className);
            }
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
                    tempOrder.setTotalPrice(products.get(productID).getPrice() + tempOrder.getTotalPrice());
                    tempOrder.addComponent(products.get(productID));
                    return true;
                }
            }

            // first time you order for specific username (yourself, others)
            Order currentOrder = new Order(orders.size(), (username.equals(user.getUsername()))?"simple":"compound", username);
            currentOrder.setTotalPrice(products.get(productID).getPrice() + currentOrder.getTotalPrice());
            currentOrder.addComponent(products.get(productID));
            userOrders.addComponent(currentOrder);
            return true;

        } catch (Exception e) {
            System.out.println("Exception in addProductToOrder as" + e.getMessage());
            return false;
        }

    }


    private void sendCancelNotify(User user, String orderID, String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        Order userOrder = (Order) user.getOrder();
        userOrder.setStatus("cancelled");
        orders.put(userOrder.getID(), userOrder);
        userOrder = new Order(orders.size(), "none", user.getUsername());

        Notification notification = new Notification();
        MessageTemplate message = new Cancellation();
        Class<?> clazz = Class.forName(className); //TODO: not experimented yet 
        Object channel =  clazz.getDeclaredConstructor().newInstance();
        message.createMessage(userOrder, user);
        notification.setMessage(message);
        notification.setChannel((ChannelStrategy)channel);
        notificationsQueue.add(notification);
        stats.cancelTempCounter+=1;
    }
}
