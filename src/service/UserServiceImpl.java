package src.service;
import src.model.Order;
import src.model.User;

import static src.util.Database.orders;
import static src.util.Database.users;


public class UserServiceImpl implements UserService {

    @Override
    public Boolean addUser(User p) {
        try {
            // if username exists already
            if(users.get(p.getUsername()) != null){
                return false;
            }
            // username doeasn't exist
            p.setOrder(new Order(orders.size(), "none", p.getUsername()));
            users.put(p.getUsername(), p);
            
        } catch (Exception e) {
            System.out.println("Exception in addUser as" + e.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public User getUser(String username, String password) {
        try {
            // user exist
            if(users.get(username) != null && users.get(username).getPassword().equals(password)){
                users.get(username).setOrder(new Order(orders.size(), "compound", username));
                return users.get(username);
            }
        } catch (Exception e) {
            System.out.println("Exception in getUser as" + e.getMessage());
            return null;
        }
        return null;
    }


    @Override
    public Boolean addBalance(User user, Double balance) {
        try {
            // user exist
            if(user.getUsername() != null){
                user.setBalance(balance + user.getBalance());
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception in addBalance as" + e.getMessage());
            return false;
        }
        return false;
    }
}
