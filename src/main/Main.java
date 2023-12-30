package src.main;

import src.model.Category;
import src.model.Loc;
import src.model.Product;
import src.model.User;
import src.service.OrderService;
import src.service.OrderServiceImpl;
import src.service.ProductService;
import src.service.ProductServiceImpl;
import src.service.StatisticsService;
import src.service.StatisticsServiceImpl;
import src.service.UserServiceImpl;
import src.util.Database;
import src.util.Statistics;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl personServiceImpl = new UserServiceImpl();
        
        User p1 = new User("k", "mazen@gmail.com", "1", "01101111002", "Doki", 1000.0);
        User p2 = new User("j", "mazen@gmail.com", "1", "0219218341", "Doki", 650.0);

        personServiceImpl.addUser(p1);
        personServiceImpl.addUser(p2);

        personServiceImpl.getUser("k", "1");

        Product s1 = new Product("0133142", "iphone", "apple", Category.SmartPhones, 500.0, 22);
        Product s2 = new Product("0133143", "nokia2", "nokia", Category.SmartPhones, 150.0, 21);
        Database.products.put("0133142",s1);
        Database.products.put("0133143",s2);

        for (Product p : Database.products.values()) {
            System.out.println(p.getName() + ", " + p.getStock());
        }
        // personServiceImpl.addPerson(p2);
        OrderService orderService = new OrderServiceImpl();
        orderService.addProductToOrder(p1,"0133142",1,"k");
        orderService.addProductToOrder(p1,"0133143",1,"k");
        orderService.addProductToOrder(p1,"0133142",1,"j");
        orderService.addProductToOrder(p1,"0133143",1,"j");
//        System.out.println(p1.getOrder().toString());
        orderService.placeOrder(p1,"src.Channels.SMS");
        orderService.shipOrder(p1,"src.Channels.SMS");

        System.out.println(p1.getBalance());
// //        System.out.println(orderService.getOrder(p1).toString());

//         System.out.println("get Person by id: 1");
//         System.out.println(personServiceImpl.getUser("k", "1"));

//         StatisticsService ss = new StatisticsServiceImpl();
//         System.out.println("emailllllll: " + ss.getMostNotifyEmail());
//         System.out.println("phoneeeeeee: " + ss.getMostNotifyPhone());
//         System.out.println("templateeee: " + ss.getMostNotifyTemp());

//         ProductService prod = new ProductServiceImpl();
//         System.out.println("get all products: " + prod.getAllProducts());
//         System.out.println("get specific product: " + prod.getProduct("0133q143"));

        for (Product p : Database.products.values()) {
            System.out.println(p.getName() + ", " + p.getStock());
        }

    }
}
