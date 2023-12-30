package com.example.notificationorderapp.util;

import com.example.notificationorderapp.model.ComponentOrder;
import com.example.notificationorderapp.model.Product;
import com.example.notificationorderapp.model.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.example.notificationorderapp.model.Notification;

public class Database{

    public static Map<String, User> users = new HashMap<String, User>();

    public static Map<String, Product> products = new HashMap<String, Product>();
    
    public static Map<Integer, ComponentOrder> orders = new HashMap<Integer, ComponentOrder>();

    public static Statistics stats = new Statistics();

    public static Queue<Notification> notificationsQueue = new LinkedList<>();
      
}
