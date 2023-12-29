package src.main;

import src.model.User;
import src.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl personServiceImpl = new UserServiceImpl();
        
        User p1 = new User();
        p1.setUsername("k");
        p1.setPassword("1");
        
        personServiceImpl.addUser(p1);
        
        // personServiceImpl.addPerson(p2);
        
        System.out.println("get Person by id: 1");
        System.out.println(personServiceImpl.getUser("k", "1"));
        
    }
}
