package src.service;

import java.util.ArrayList;

import src.model.Product;

public interface ProductService {
    
    public ArrayList<Product> getAllProducts();

    public Product getProduct(String productID);

}
