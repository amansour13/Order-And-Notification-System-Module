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
		Database.products.put("111", s1);

		Product s2 = new Product("112", "nokia2", "nokia", Category.SmartPhones, 150.0, 21);
		Database.products.put("112", s2);

		Product s3 = new Product("113", "galaxy-s21", "samsung", Category.SmartPhones, 800.0, 15);
		Database.products.put("113", s3);

		Product s4 = new Product("114", "pixel-5", "google", Category.SmartPhones, 700.0, 10);
		Database.products.put("114", s4);

		Product s5 = new Product("115", "ipad-pro", "apple", Category.Tablets, 1100.0, 20);
		Database.products.put("115", s5);

		Product s6 = new Product("116", "galaxy-tab-s7", "samsung", Category.Tablets, 650.0, 8);
		Database.products.put("116", s6);

		Product s7 = new Product("117", "surface-pro-7", "microsoft", Category.Tablets, 950.0, 5);
		Database.products.put("117", s7);

		Product s8 = new Product("118", "thinkpad-x1", "lenovo", Category.Laptop, 1200.0, 4);
		Database.products.put("118", s8);

		Product s9 = new Product("119", "macbook-pro", "apple", Category.Laptop, 2400.0, 3);
		Database.products.put("119", s9);

		Product s10 = new Product("120", "alienware-m15", "dell", Category.Laptop, 2000.0, 6);
		Database.products.put("120", s10);

		Product s11 = new Product("121", "inspiron-15", "dell", Category.PC, 450.0, 12);
		Database.products.put("121", s11);

		Product s12 = new Product("122", "omen-30l", "hp", Category.PC, 1500.0, 9);
		Database.products.put("122", s12);

	}

	public static void main(String[] args) {
		populateDatabase();
		SpringApplication.run(NotificationOrderAppApplication.class, args);
	}

}
