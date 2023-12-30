package src.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements ComponentOrder {

    private int ID;
    private LocalDate timeShip;
    private ArrayList<ComponentOrder> components = new ArrayList<>();
    private Double shippingFees= 0.0;
    private String orderType;
    private String owner;
    private String status;
    private Double totalPrice = 0.0;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(int iD, String orderType, String owner) {
        ID = iD;
        this.orderType = orderType;
        this.owner = owner;
        status = "none";
        totalPrice = 0.0;
    }

    public ArrayList<ComponentOrder> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<ComponentOrder> components) {
        this.components = components;
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public LocalDate getTimeShip() {
        return timeShip;
    }

    public void setTimeShip(LocalDate timeShip) {
        this.timeShip = timeShip;
    }

    public Double getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(Double shippingFees) {
        this.shippingFees = shippingFees;
    }

    @Override
    public void addComponent(ComponentOrder component) {
        components.add(component);
    }

    @Override
    public void removeComponent(ComponentOrder component) {
        components.remove(component);
    }

    @Override
    public ComponentOrder getChild(int i) {
        return components.get(i);
    }

    @Override
    public String toString() {
        String result = "ID: " + ID + "\n";
        result += "Owner: " + owner + "\n";
        // result += timeShip + "\n";
        if (orderType.equals("simple")) {
            result += "total price: " + totalPrice + "\n";
            for (ComponentOrder componentOrder : components) {
                result += componentOrder.toString();
                result += "\n----------------------------\n";
            }
        }
        else if (orderType.equals("compound")) {
            result += "Status: " +  status + "\n";
            for (ComponentOrder componentOrder : components) {
                Order tempOrder = (Order) componentOrder;
                // result += tempOrder.ID + "\n";
                componentOrder.toString();
                for (ComponentOrder tempComponenOrder : tempOrder.components) {
                    result += tempComponenOrder.toString();
                    result += "\n----------------------------\n";
                }
            }
        }
        return result;
    }

}
