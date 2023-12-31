package com.example.notificationorderapp.service;

import java.util.ArrayList;

import com.example.notificationorderapp.model.Product;

public interface ProductService {
    
    public ArrayList<Product> getAllProducts();

    public Product getProduct(String productID);

}
