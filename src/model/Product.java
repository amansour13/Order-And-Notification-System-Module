package src.model;
public class Product implements ComponentOrder{
    private String serialNumber;
    private String name;
    private String vendor;
    private Category category;
    private Double price;
    private int stock;

    public Product(Product other) {
        this.serialNumber = other.serialNumber;
        this.name = other.name;
        this.vendor = other.vendor; 
        this.category = other.category;
        this.price = other.price;
        this.stock = other.stock;
    }
    
    public Product(String serialNumber, String name, String vendor, Category category, Double price, int stock) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
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

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should not happen since Person implements Cloneable
            throw new AssertionError();
        }
    }
}
