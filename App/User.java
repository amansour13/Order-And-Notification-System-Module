package App;

public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String location;
    private String nearByLocation;
    private double balance;

    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getNearByLocation() {
        return nearByLocation;
    }
    public void setNearByLocation(String nearByLocation) {
        this.nearByLocation = nearByLocation;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    
}
