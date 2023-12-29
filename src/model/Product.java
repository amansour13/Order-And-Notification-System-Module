package src.model;
public class Product implements ComponentOrder{
    private String serialNumber;
    private String name;
    private String vendor;
    private Category category;
    private Double price;
    private int stock;
    
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public void addComponent(ComponentOrder component) {}
    @Override
    public void removeComponent(ComponentOrder component) {}
    @Override
    public ComponentOrder getChild(int i) {return null;}
    
    @Override
    public String toString() {
        return serialNumber+"::"+name+"::"+vendor+"::"+category.name()+"::"+Double.toString(price)+"::"+Integer.toString(stock);
    }
}
