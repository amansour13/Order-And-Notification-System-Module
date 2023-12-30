package com.example.notificationorderapp.service;

import java.util.ArrayList;
import java.util.Map;

import com.example.notificationorderapp.model.Product;
import static com.example.notificationorderapp.util.Database.products;

public class ProductServiceImpl implements ProductService{

    @Override
    public ArrayList<Product> getAllProducts() {
        try {
            ArrayList<Product> result = new ArrayList<>();
            for (Map.Entry<String, Product> entry : products.entrySet()) {
                Product value = entry.getValue();
                result.add(value);
            }
            return result;
        } catch (Exception e) {
            System.out.println("Exception in getAllProducts as" + e.getMessage());
            return null;
        }
    }

    @Override
    public Product getProduct(String productID) {
        try {
            return products.get(productID);
        } catch (Exception e) {
            System.out.println("Exception in getProduct as" + e.getMessage());
            return null;
        }
    }
    
}
