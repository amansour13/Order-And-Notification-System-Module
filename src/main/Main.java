package src.main;

import src.model.Category;
import src.model.Loc;
import src.model.Product;
import src.model.User;
import src.service.OrderService;
import src.service.OrderServiceImpl;
import src.service.UserServiceImpl;
import src.util.Database;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl personServiceImpl = new UserServiceImpl();
        
        User p1 = new User();
        p1.setUsername("k");
        p1.setPassword("1");
        p1.setBalance(1000.0);
        p1.setEmail("mazen@gamil.com");
        p1.setPhone("0101111002");
        p1.setLocation("Dokki");
        personServiceImpl.addUser(p1);
        Product s1=new Product("0133142", "iphone", "apple", Category.SmartPhones, 500.0, 22);
        Product s2=new Product("0133143", "nokia2", "nokia", Category.SmartPhones, 150.0, 21);
        Database.products.put("0133142",s1);
        Database.products.put("0133143",s2);
        // personServiceImpl.addPerson(p2);
        OrderService orderService = new OrderServiceImpl();
        System.out.println(orderService.addProductToOrder(p1,"0133142",1,"k"));
        System.out.println(orderService.addProductToOrder(p1,"0133143",1,"k"));
//        System.out.println(p1.getOrder().toString());
        orderService.placeOrder(p1,"src.Channels.SMS");
        orderService.shipOrder(p1,"src.Channels.SMS");

        System.out.println(p1.getBalance());
//        System.out.println(orderService.getOrder(p1).toString());

        System.out.println("get Person by id: 1");
        System.out.println(personServiceImpl.getUser("k", "1"));


    }
}
