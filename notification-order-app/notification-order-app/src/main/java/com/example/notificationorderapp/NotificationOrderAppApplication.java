package com.example.notificationorderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.notificationorderapp.model.Category;
import com.example.notificationorderapp.model.Product;
import com.example.notificationorderapp.util.Database;

@SpringBootApplication
public class NotificationOrderAppApplication {
	private static void populateDatabase() {
		Product s1 = new Product("111", "iphone", "apple", Category.SmartPhones, 500.0, 22);
        Product s2 = new Product("222", "nokia2", "nokia", Category.SmartPhones, 150.0, 21);
        Database.products.put("111",s1);
        Database.products.put("222",s2);
	}

	public static void main(String[] args) {
		populateDatabase();
		SpringApplication.run(NotificationOrderAppApplication.class, args);
	}

}
