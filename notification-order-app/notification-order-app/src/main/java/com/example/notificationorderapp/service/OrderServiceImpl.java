package com.example.notificationorderapp.service;

import com.example.notificationorderapp.Channels.ChannelStrategy;
import com.example.notificationorderapp.Channels.Email;
import com.example.notificationorderapp.Channels.SMS;

import com.example.notificationorderapp.model.ComponentOrder;
import com.example.notificationorderapp.model.Order;
import com.example.notificationorderapp.model.Product;
import com.example.notificationorderapp.model.User;
import com.example.notificationorderapp.model.Messages.Cancellation;
import com.example.notificationorderapp.model.Messages.MessageTemplate;
import com.example.notificationorderapp.model.Messages.Placement;
import com.example.notificationorderapp.model.Messages.Shipment;
import com.example.notificationorderapp.model.Notification;

import static com.example.notificationorderapp.util.Database.orders;
import static com.example.notificationorderapp.util.Database.products;
import static com.example.notificationorderapp.util.Database.users;
import static com.example.notificationorderapp.util.Database.stats;
import static com.example.notificationorderapp.util.Database.notificationsQueue;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class OrderServiceImpl implements OrderService{
    
    @Override
    public void createOrder(User user) {
        try {
            user.setOrder(new Order(orders.size(), "compound", user.getUsername()));
            ((Order) user.getOrder()).setShippingFees(50.0);
            orders.put(((Order) user.getOrder()).getID(), (Order) user.getOrder());
        } catch (Exception e) {
            System.out.println("Exception in createOrder as" + e.getMessage());
        }
    }

    @Override
    public boolean placeOrder(User user, String className) {
        try {
            Order order = (Order) user.getOrder();
            if (!order.getStatus().equals("placed")){
                order.setStatus("placed");
                 
                Notification notification = new Notification();
                MessageTemplate message = new Placement();
                Class<?> clazz = Class.forName(className); //TODO: not experimented yet 
                Object channel =  clazz.getDeclaredConstructor().newInstance();

                for (ComponentOrder o : order.getComponents()) {
                    User u = users.get(((Order) o).getOwner());
                    message.createMessage(o, u);
                    notification.setMessage(message);
                    notification.setChannel((ChannelStrategy)channel);
                    notification.getChannel().send(u);
                    System.out.println(notification.getMessage().getContent());
                    addToNotificationsQueue(notification);
                }
                stats.placeTempCounter++;
            }
            return true;
            
        } catch (Exception e) {
            System.out.println("Exception in placeOrder as" + e.getMessage());
            return false;
        }
    }

    @Override
    public String shipOrder(User user, String className) {
       try {
            Order order = (Order) user.getOrder();

            if (order.getStatus().equals("placed")){
                String paidStatus = payForEachOrder(order);
                if (paidStatus!= "success") {
                    return paidStatus;
                }

                order.setStatus("shipped");
                order.setTimeShip(LocalDate.now());
                orders.put(order.getID(), order);
                Notification notification = new Notification();
                MessageTemplate message = new Shipment();
                Class<?> clazz = Class.forName(className); //TODO: not experimented yet 
                Object channel =  clazz.getDeclaredConstructor().newInstance();

                for (ComponentOrder o : order.getComponents()) {
                    User u = users.get(((Order) o).getOwner());
                    message.createMessage(o, u);
                    notification.setMessage(message);
                    notification.setChannel((ChannelStrategy)channel);
                    notification.getChannel().send(u);
                    System.out.println(notification.getMessage().getContent());
                    addToNotificationsQueue(notification);
                }                    
                stats.shipTempCounter++;
                user.setOrder(null);
                return "success";
            } else {
                return  "order not placed";
            }
        } catch (Exception e) {
            System.out.println("Exception in shipOrder as" + e.getMessage());
            return "failed";
        }
    }

    private String payForEachOrder(ComponentOrder order) {
        double feeForEach = ((Order)order).getShippingFees() / ((Order)order).getComponents().size();

        for (ComponentOrder co : ((Order)order).getComponents()) {
            Order o = (Order) co;
            if (users.get(o.getOwner()).getBalance() < o.getTotalPrice() + feeForEach) {
                return "Not enough money, for user: " + users.get(o.getOwner()).getUsername();
            }
            for (ComponentOrder pro : o.getComponents()) {
                Product p = (Product) pro;
                if(p.getStock() > products.get(p.getSerialNumber()).getStock() ) {
                    return "Not enough stock, for prodcut: " + products.get(p.getSerialNumber()).getName() + " stock: " +products.get(p.getSerialNumber()).getStock() + " desired quantity: " + p.getStock();
                }
            }
        }
        for (ComponentOrder co : ((Order)order).getComponents()) {
            Order o = (Order) co;
            User user = users.get(o.getOwner());
            user.setBalance(user.getBalance() - o.getTotalPrice() - feeForEach);

            for (ComponentOrder pro : o.getComponents()) {
                Product p = (Product) pro;
                products.get(p.getSerialNumber()).setStock(products.get(p.getSerialNumber()).getStock() - p.getStock());
            }

        }

        return "success";
    }

    @Override
    public String cancelOrder(User user ,String className) {
        try {
            Order userOrder = (Order) user.getOrder();
            // cacnel -> (placed)
            if (userOrder.getStatus().equals("placed"))
            {
                sendCancelNotify(user,  className);
                return "success";
            }

            // cancel -> (shipped)
            // Check if the difference is exactly one day
            long daysDifference = ChronoUnit.DAYS.between(LocalDate.now(), userOrder.getTimeShip());
            boolean checkTime = Math.abs(daysDifference) == 1;
            if (userOrder.getStatus().equals("shipped") && checkTime)
            {
                sendCancelNotify(user,  className);
                return "success";
            }
            else{
                return "Order cancellation failed due to the cancel request period time has been passed";
            }
            
        } catch (Exception e) {
            System.out.println("Exception in cancelOrder as" + e.getMessage());
            return " failed";
        }
    }

    @Override
    public ComponentOrder getOrder(int id) {
        try {
            return orders.get(id);
        } catch (Exception e) {
            System.out.println("Exception in getOrder as" + e.getMessage());
            return null;
        }
    }

    @Override
    // TODO: comapring strings, is not the best
    public String addProductToOrder(User user, String productID, int quantity, String username) {
        try {
            if (users.get(username) == null){
                return "Owner doesn't exsist";
            }
            if (products.get(productID) == null){
                return "Product doesn't exsist";
            }
            if(products.get(productID).getStock() < quantity){
                return "Stock isn't enough";
            }
            
            Order userOrders = (Order) user.getOrder();// 1 order
            for (ComponentOrder order : userOrders.getComponents()) {
                Order tempOrder = (Order) order;
                if (tempOrder.getOwner().equals(username)){
                    tempOrder.setTotalPrice(products.get(productID).getPrice() * quantity + tempOrder.getTotalPrice());
                    Product product = new Product(products.get(productID));
                    product.setStock(quantity);
                    tempOrder.addComponent(product);
                    return "success";
                }
            }

            // first time you order for specific username (yourself, others)
            // Order currentOrder = new Order(orders.size(), (username.equals(user.getUsername()))?"simple":"compound", username);
            Order currentOrder = new Order(orders.size(), "simple", username);
            currentOrder.setTotalPrice(products.get(productID).getPrice()*quantity + currentOrder.getTotalPrice());

            Product product = new Product(products.get(productID));
            product.setStock(quantity);
            currentOrder.addComponent(product);

            userOrders.addComponent(currentOrder);
            user.setOrder(userOrders);
            return "success";

        } catch (Exception e) {
            System.out.println("Exception in addProductToOrder as" + e.getMessage());
            return "success";
        }

    }


    private void sendCancelNotify(User user, String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
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
        notification.getChannel().send(user);
        addToNotificationsQueue(notification);
        stats.cancelTempCounter+=1;
    }

     private void addToNotificationsQueue(Notification notification){
          notificationsQueue.add(notification);
         long delay = 2;
         long delayInNanos = TimeUnit.MINUTES.toNanos(delay);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        LocalDateTime scheduledTime = LocalDateTime.now().plusNanos(TimeUnit.NANOSECONDS.toNanos(delayInNanos));
        scheduler.schedule(() -> {
            
            notificationsQueue.remove(notification);
            
        }, LocalDateTime.now().until(scheduledTime, ChronoUnit.NANOS), TimeUnit.NANOSECONDS);
      
    }
}
