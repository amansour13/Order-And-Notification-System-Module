package src.util;

import src.model.ComponentOrder;
import src.model.Product;
import src.model.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import src.model.Notification;

public class Database{

    public static Map<String, User> users = new HashMap<String, User>();

    public static Map<String, Product> products = new HashMap<String, Product>();
    
    public static Map<Integer, ComponentOrder> orders = new HashMap<Integer, ComponentOrder>();

    public static Statistics stats = new Statistics();

    public static Queue<Notification> notificationsQueue = new LinkedList<>();
      
}
