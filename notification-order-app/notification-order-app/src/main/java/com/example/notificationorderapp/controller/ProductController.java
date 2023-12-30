package com.example.notificationorderapp.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationorderapp.model.Product;
import com.example.notificationorderapp.service.ProductService;
import com.example.notificationorderapp.service.ProductServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/")
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<ArrayList<Product>> getAllProducts() {
        ProductService productService = new ProductServiceImpl();
        ArrayList<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        System.out.println(id);
        ProductService productService = new ProductServiceImpl();
        Product product = productService.getProduct(id);
        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}