package com.example.notificationorderapp.service;

import com.example.notificationorderapp.Channels.ChannelStrategy;
import com.example.notificationorderapp.Langauges.ILangauge;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


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
    public ResponseEntity<String> placeOrder(User user, ChannelStrategy channel, ILangauge langauge) {
        try {
            Order order = (Order) user.getOrder();
            if (order.getStatus().equals("placed")) {
                return new ResponseEntity<>("Order is already placed", HttpStatus.CONFLICT);
            }

            if (order.getStatus().equals("none")){
                ResponseEntity<String> paidStatus = payForEachOrder(order);
                if (paidStatus.getStatusCode() != HttpStatus.OK) {
                    return paidStatus;
                }
                order.setStatus("placed");
                 
                Notification notification = new Notification();
                MessageTemplate message = new Placement();

                for (ComponentOrder o : order.getComponents()) {
                    User u = users.get(((Order) o).getOwner());
                    message.createMessage(o, u, langauge);
                    notification.setMessage(message);
                    notification.setChannel(channel);
                    notification.setUser(user);
                    addToNotificationsQueue(notification);
                    
                }
                stats.placeTempCounter++;
                return new ResponseEntity<>("Order Placed Successfully, orderId: " + order.getID(), HttpStatus.OK);
                
                
            }
            return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
            
        } catch (Exception e) {
            System.out.println("Exception in placeOrder as" + e.getMessage());
            return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> shipOrder(User user, ChannelStrategy channel, ILangauge langauge) {
       try {
            Order order = (Order) user.getOrder();

            if (order.getStatus().equals("placed")){
                // pay shipping fees
                ResponseEntity<String> paidStatus = payForEachOrderShipFee(order);
                if (paidStatus.getStatusCode() != HttpStatus.OK) {
                    return paidStatus;
                }

                order.setStatus("shipped");
                order.setTimeShip(LocalDate.now());
                orders.put(order.getID(), order);
                Notification notification = new Notification();
                MessageTemplate message = new Shipment();

                for (ComponentOrder o : order.getComponents()) {
                    User u = users.get(((Order) o).getOwner());
                    message.createMessage(o, u, langauge);
                    notification.setMessage(message);
                    notification.setChannel(channel);
                    notification.setUser(user);
                    addToNotificationsQueue(notification);
                }                    
                stats.shipTempCounter++;
                user.setOrder(null);
                return new ResponseEntity<>("Succeess, orderId: " + order.getID(), HttpStatus.OK);
            } else {
                return  new ResponseEntity<>("order not placed", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            System.out.println("Exception in shipOrder as" + e.getMessage());
            return  new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<String> payForEachOrder(ComponentOrder order) {

        for (ComponentOrder co : ((Order)order).getComponents()) {
            Order o = (Order) co;
            if (users.get(o.getOwner()).getBalance() < o.getTotalPrice() ) {
                return new ResponseEntity<>("Not enough money for paying Order Fees, for user: " + users.get(o.getOwner()).getUsername(), HttpStatus.CONFLICT);
            }
            for (ComponentOrder pro : o.getComponents()) {
                Product p = (Product) pro;
                if(p.getStock() > products.get(p.getSerialNumber()).getStock() ) {
                    return new ResponseEntity<>("Not enough stock, for prodcut: " + products.get(p.getSerialNumber()).getName() + " stock: " +products.get(p.getSerialNumber()).getStock() + " desired quantity: " + p.getStock(), HttpStatus.CONFLICT);
                }
            }
        }
        for (ComponentOrder co : ((Order)order).getComponents()) {
            Order o = (Order) co;
            User user = users.get(o.getOwner());
            user.setBalance(user.getBalance() - o.getTotalPrice());

            for (ComponentOrder pro : o.getComponents()) {
                Product p = (Product) pro;
                products.get(p.getSerialNumber()).setStock(products.get(p.getSerialNumber()).getStock() - p.getStock());
            }

        }

        return new ResponseEntity<>("Succeess, orderId: " + ((Order)order).getID(), HttpStatus.OK);
    }

    private ResponseEntity<String> payForEachOrderShipFee(ComponentOrder order) {
        double feeForEach = ((Order)order).getShippingFees() / ((Order)order).getComponents().size();

        for (ComponentOrder co : ((Order)order).getComponents()) {
            Order o = (Order) co;
            if (users.get(o.getOwner()).getBalance() < feeForEach) {
                return new ResponseEntity<>("Not enough money for Shipping Fees, for user: " + users.get(o.getOwner()).getUsername(), HttpStatus.CONFLICT);
            }
        }
        for (ComponentOrder co : ((Order)order).getComponents()) {
            Order o = (Order) co;
            User user = users.get(o.getOwner());
            user.setBalance(user.getBalance() - feeForEach);
        }

        return new ResponseEntity<>("Succeess, orderId: " + ((Order)order).getID(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cancelOrder(User user , ChannelStrategy channel, ILangauge langauge, String orderId) {
//        try {
            Order order = (Order)orders.get(Integer.parseInt(orderId));
            if (order == null || !order.getOwner().equals(user.getUsername())) {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }

            if (order.getStatus().equals("cancelled")) {
                return new ResponseEntity<>("Order is already cancelled", HttpStatus.CONFLICT);
            }

            // cacnel -> (placed)
            if (order.getStatus().equals("placed"))
            {
                returnMoneyForEachOrder(order);
                sendCancelNotify(user,  channel, langauge, order);
                user.setOrder(null);
                return new ResponseEntity<>("Order cancelled successfully", HttpStatus.OK);
            }

            // cancel -> (shipped)
            // Check if the difference is exactly one day
            if (order.getStatus().equals("shipped"))
            {
                long daysDifference = ChronoUnit.DAYS.between(LocalDate.now(), order.getTimeShip());
                boolean checkTime = Math.abs(daysDifference) <= 1;
                if (checkTime){
                    returnMoneyForEach(order);
                    sendCancelNotify(user, channel, langauge, order);
                    return new ResponseEntity<>("Order cancelled successfully", HttpStatus.OK);
                    
                }else{ 
                    return new ResponseEntity<>("Order cancellation failed due to the cancel request period time has been passed", HttpStatus.CONFLICT);
                }
            }

            return new ResponseEntity<>("Couldn't cancel, the order is not placed nor shipped", HttpStatus.CONFLICT);
            
//        } catch (Exception e) {
//            System.out.println("Exception in cancelOrder as" + e.getMessage());
//            return new ResponseEntity<>("Internal Error", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
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

    private ResponseEntity<String> returnMoneyForEach(ComponentOrder order) {
        double feeForEach = ((Order)order).getShippingFees() / ((Order)order).getComponents().size();

        for (ComponentOrder co : ((Order)order).getComponents()) {
            Order o = (Order) co;
            User user = users.get(o.getOwner());
            user.setBalance(user.getBalance() + o.getTotalPrice() + feeForEach);

            for (ComponentOrder pro : o.getComponents()) {
                Product p = (Product) pro;
                products.get(p.getSerialNumber()).setStock(products.get(p.getSerialNumber()).getStock() + p.getStock());
            }

        }

        return new ResponseEntity<>("Succeess, orderId: " + ((Order)order).getID(), HttpStatus.OK);
    }

    private ResponseEntity<String> returnMoneyForEachOrder(ComponentOrder order) {

        for (ComponentOrder co : ((Order)order).getComponents()) {
            Order o = (Order) co;
            User user = users.get(o.getOwner());
            user.setBalance(user.getBalance() + o.getTotalPrice());

            for (ComponentOrder pro : o.getComponents()) {
                Product p = (Product) pro;
                products.get(p.getSerialNumber()).setStock(products.get(p.getSerialNumber()).getStock() + p.getStock());
            }

        }

        return new ResponseEntity<>("Succeess, orderId: " + ((Order)order).getID(), HttpStatus.OK);
    }

    @Override
    // TODO: comapring strings, is not the best
    public ResponseEntity<String> addProductToOrder(User user, String productID, int quantity, String username) {
        try {
            User owner = users.get(username);
            if (owner == null){
                return new ResponseEntity<>("Owner doesn't exsist", HttpStatus.CONFLICT);
            }
            if (products.get(productID) == null){
                return new ResponseEntity<>("Product doesn't exsist", HttpStatus.CONFLICT);
            }
            if (products.get(productID).getStock() < quantity){
                return new ResponseEntity<>("Stock isn't enough", HttpStatus.CONFLICT);
            }
            if (user.getNearByLoc() != owner.getNearByLoc()) {
                return new ResponseEntity<>("You cannot order to different nearby location", HttpStatus.CONFLICT);
            }
            
            Order userOrder = (Order) user.getOrder();// 1 order
            for (ComponentOrder order : userOrder.getComponents()) {
                Order tempOrder = (Order) order;
                if (tempOrder.getOwner().equals(username)){
                    tempOrder.setTotalPrice(products.get(productID).getPrice() * quantity + tempOrder.getTotalPrice());
                    Product product = new Product(products.get(productID));
                    product.setStock(quantity);
                    tempOrder.addComponent(product);
                    return new ResponseEntity<>("Succeess, orderId: " + userOrder.getID(), HttpStatus.OK);
                }
            }

            // first time you order for specific username (yourself, others)
            // Order currentOrder = new Order(orders.size(), (username.equals(user.getUsername()))?"simple":"compound", username);
            Order currentOrder = new Order(orders.size(), "simple", username);
            currentOrder.setTotalPrice(products.get(productID).getPrice()*quantity + currentOrder.getTotalPrice());

            Product product = new Product(products.get(productID));
            product.setStock(quantity);
            currentOrder.addComponent(product);

            userOrder.addComponent(currentOrder);
            user.setOrder(userOrder);
            return new ResponseEntity<>("Succeess, orderId: " + userOrder.getID(), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Exception in addProductToOrder as" + e.getMessage());
            return new ResponseEntity<>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    private void sendCancelNotify(User user, ChannelStrategy channel, ILangauge langauge, Order userOrder) {
        userOrder.setStatus("cancelled");
        orders.put(userOrder.getID(), userOrder);

        Notification notification = new Notification();
        MessageTemplate message = new Cancellation();
        message.createMessage(userOrder, user, langauge);
        notification.setMessage(message);
        notification.setChannel(channel);
        notification.setUser(user);
        addToNotificationsQueue(notification);
        stats.cancelTempCounter+=1;
    }

     private void addToNotificationsQueue(Notification notification){
        notificationsQueue.add(notification);
    }
}
