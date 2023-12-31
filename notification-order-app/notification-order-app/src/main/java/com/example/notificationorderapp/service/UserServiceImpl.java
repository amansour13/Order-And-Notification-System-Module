package com.example.notificationorderapp.service;
import com.example.notificationorderapp.model.Order;
import com.example.notificationorderapp.model.User;

import static com.example.notificationorderapp.util.Database.orders;
import static com.example.notificationorderapp.util.Database.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class UserServiceImpl implements UserService {

    @Override
    public Boolean addUser(User p) {
        try {
            // if username exists already
            if(users.get(p.getUsername()) != null){
                return false;
            }
            
            // username doeasn't exist
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
            User user = users.get(username);
            if(user != null && user.getPassword().equals(password)){

                return user;
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
