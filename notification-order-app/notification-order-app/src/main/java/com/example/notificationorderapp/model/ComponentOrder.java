package com.example.notificationorderapp.model;

public interface ComponentOrder {
    public void addComponent(ComponentOrder component);
    public void removeComponent(ComponentOrder component);
    public ComponentOrder getChild(int i);
}
